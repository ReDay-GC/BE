package ReDay.analysis.infrastructure;

import ReDay.application.exception.BusinessException;
import ReDay.application.exception.ErrorCode;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
@RequiredArgsConstructor
public class AnalysisAiService {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private final RestClient openAIRestClient;

    private static final String MODEL = "gpt-4o-mini";
    private static final String SYSTEM_PROMPT = """
            사용자의 월간 기록을 분석하여 반드시 다음 JSON 형식으로만 응답하세요:
            {
              "insight": "이번 달 활동에 대한 2~3문장 한국어 인사이트",
              "topPeople": [{"name": "이름", "count": 횟수}],
              "topActivities": [{"activityType": "활동명", "percentage": 비율}]
            }
            topPeople은 기록에서 언급된 사람 이름 상위 5명, topActivities는 활동 유형 상위 5개를 내림차순으로 반환하세요.
            기록이 충분하지 않으면 빈 배열로 반환하세요.
            """;

    public AiInsightResult generateInsight(int year, int month, String recordsText) {
        String userMessage = year + "년 " + month + "월 기록:\n" + recordsText;

        Map<String, Object> requestBody = Map.of(
                "model", MODEL,
                "messages", List.of(
                        Map.of("role", "system", "content", SYSTEM_PROMPT),
                        Map.of("role", "user", "content", userMessage)
                ),
                "response_format", Map.of("type", "json_object")
        );

        try {
            String responseBody = openAIRestClient.post()
                    .uri("/v1/chat/completions")
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(requestBody)
                    .retrieve()
                    .body(String.class);

            return parseResponse(responseBody);
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            throw new BusinessException(ErrorCode.INSIGHT_GENERATION_FAILED);
        }
    }

    private AiInsightResult parseResponse(String responseBody) {
        try {
            JsonNode root = OBJECT_MAPPER.readTree(responseBody);
            String content = root.path("choices").get(0).path("message").path("content").asText();
            JsonNode contentNode = OBJECT_MAPPER.readTree(content);

            String insight = contentNode.path("insight").asText();
            List<PersonData> people = OBJECT_MAPPER.convertValue(
                    contentNode.path("topPeople"), new TypeReference<List<PersonData>>() {});
            List<ActivityData> activities = OBJECT_MAPPER.convertValue(
                    contentNode.path("topActivities"), new TypeReference<List<ActivityData>>() {});

            return new AiInsightResult(insight, people, activities);
        } catch (Exception e) {
            throw new BusinessException(ErrorCode.INSIGHT_GENERATION_FAILED);
        }
    }

    public record AiInsightResult(String insight, List<PersonData> topPeople, List<ActivityData> topActivities) {}

    public record PersonData(String name, int count) {}

    public record ActivityData(String activityType, int percentage) {}
}
