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
public class AiEmbeddingClient {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    private static final String EMBEDDING_MODEL = "text-embedding-3-small";

    private final RestClient openAIRestClient;
    private final RestClient aiServerRestClient;

    public AiEmbeddingClient(RestClient openAIRestClient, RestClient aiServerRestClient) {
        this.openAIRestClient = openAIRestClient;
        this.aiServerRestClient = aiServerRestClient;
    }

    // 실패해도 기억 생성에 영향 없도록 내부에서 예외를 흡수합니다.
    public void saveEmbedding(Long memoryId, String text) {
        try {
            List<Double> embedding = generateEmbedding(text);
            if (embedding.isEmpty()) return;

            String embeddingJson = OBJECT_MAPPER.writeValueAsString(embedding);
            Map<String, Object> body = Map.of(
                    "memory_id", memoryId,
                    "embedding", embeddingJson
            );

            aiServerRestClient.post()
                    .uri("/save-embedding")
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(body)
                    .retrieve()
                    .body(String.class);

            log.info("[AiEmbeddingClient] 임베딩 저장 완료: memoryId={}", memoryId);
        } catch (Exception e) {
            log.error("[AiEmbeddingClient] 임베딩 저장 실패: memoryId={}, error={}", memoryId, e.getMessage());
        }
    }

    private List<Double> generateEmbedding(String text) {
        try {
            Map<String, Object> body = Map.of("input", text, "model", EMBEDDING_MODEL);

            String response = openAIRestClient.post()
                    .uri("/embeddings")
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(body)
                    .retrieve()
                    .body(String.class);

            JsonNode root = OBJECT_MAPPER.readTree(response);
            JsonNode embeddingNode = root.path("data").get(0).path("embedding");
            return OBJECT_MAPPER.convertValue(embeddingNode,
                    OBJECT_MAPPER.getTypeFactory().constructCollectionType(List.class, Double.class));
        } catch (Exception e) {
            log.error("[AiEmbeddingClient] 임베딩 생성 실패: {}", e.getMessage());
            return List.of();
        }
    }
}
