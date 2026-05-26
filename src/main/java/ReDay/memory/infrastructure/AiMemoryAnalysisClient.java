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
public class AiMemoryAnalysisClient {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private final RestClient aiServerRestClient;

    public AiMemoryAnalysisClient(RestClient aiServerRestClient) {
        this.aiServerRestClient = aiServerRestClient;
    }

    public AnalysisResult analyzeText(String text) {
        try {
            Map<String, Object> body = Map.of("memo_text", text, "stt_text", "");

            String response = aiServerRestClient.post()
                    .uri("/analyze-text")
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(body)
                    .retrieve()
                    .body(String.class);

            JsonNode root = OBJECT_MAPPER.readTree(response);
            return new AnalysisResult(
                    root.path("emotion").asText("NEUTRAL"),
                    root.path("activity_hint").asText("일상적인 활동"),
                    root.path("summary").asText("하루의 기록이 정리되었습니다.")
            );
        } catch (Exception e) {
            log.error("[AiMemoryAnalysisClient] /analyze-text 호출 실패: {}", e.getMessage());
            return new AnalysisResult("NEUTRAL", "일상적인 활동", "하루의 기록이 정리되었습니다.");
        }
    }

    public List<String> extractKeywords(String text) {
        try {
            Map<String, Object> body = Map.of("memo_text", text, "stt_text", "");

            String response = aiServerRestClient.post()
                    .uri("/extract-keywords")
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(body)
                    .retrieve()
                    .body(String.class);

            JsonNode root = OBJECT_MAPPER.readTree(response);
            List<String> keywords = OBJECT_MAPPER.convertValue(
                    root.path("keywords"),
                    OBJECT_MAPPER.getTypeFactory().constructCollectionType(List.class, String.class)
            );
            return keywords.isEmpty() ? List.of("일상", "기록") : keywords;
        } catch (Exception e) {
            log.error("[AiMemoryAnalysisClient] /extract-keywords 호출 실패: {}", e.getMessage());
            return List.of("일상", "기록");
        }
    }

    public record AnalysisResult(String emotion, String activityHint, String summary) {}
}
