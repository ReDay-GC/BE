package ReDay.memory.infrastructure;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Slf4j
@Component
public class AiSemanticSearchClient {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private final RestClient aiServerRestClient;

    public AiSemanticSearchClient(RestClient aiServerRestClient) {
        this.aiServerRestClient = aiServerRestClient;
    }

    // 실패 시 빈 리스트를 반환하며 호출부에서 키워드 검색으로 폴백합니다.
    public List<Long> searchSemantic(String query, List<Long> memoryIds) {
        if (memoryIds.isEmpty()) return List.of();

        Map<String, Object> requestBody = Map.of(
                "query", query,
                "memory_ids", memoryIds
        );

        try {
            String responseBody = aiServerRestClient.post()
                    .uri("/search-semantic")
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(requestBody)
                    .retrieve()
                    .body(String.class);

            return parseRankedIds(responseBody);
        } catch (Exception e) {
            log.error("[AiSemanticSearchClient] AI 서버 호출 실패: {}", e.getMessage(), e);
            return List.of();
        }
    }

    private List<Long> parseRankedIds(String responseBody) {
        try {
            JsonNode root = OBJECT_MAPPER.readTree(responseBody);
            JsonNode rankedIds = root.path("ranked_ids");
            return OBJECT_MAPPER.convertValue(rankedIds,
                    OBJECT_MAPPER.getTypeFactory().constructCollectionType(List.class, Long.class));
        } catch (Exception e) {
            log.error("[AiSemanticSearchClient] 응답 파싱 실패: {}", e.getMessage(), e);
            return List.of();
        }
    }
}
