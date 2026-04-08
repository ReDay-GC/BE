package ReDay.memory.application.dto.response;

import java.time.LocalDateTime;
import java.util.List;

public record MemoryAnalysisResponse(
        Long analysisId,
        String emotionResult,
        List<String> keywords,
        String placeSummary,
        String activitySummary,
        String overallSummary,
        LocalDateTime analyzedAt
) {
}
