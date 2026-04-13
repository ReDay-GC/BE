package ReDay.admin.application.dto.response;

public record AdminAiMonitoringStatsResponse(
        long totalRequests,
        double averageResponseTimeSec,
        double successRate
) {
}
