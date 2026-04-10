package ReDay.admin.application.dto.response;

import java.util.List;

public record AdminAiStatsResponse(
        long totalRequests,
        double averageResponseTimeSec,
        double successRate,
        List<AdminAiLogResponse> logs
) {
}
