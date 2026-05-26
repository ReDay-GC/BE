package ReDay.admin.application.dto.response;

import java.time.LocalDateTime;

public record AdminAiMonitoringLogResponse(
        Long logId,
        String memoryId,
        Long userId,
        String userName,
        String status,
        Double responseTimeSec,
        LocalDateTime processedAt
) {
}
