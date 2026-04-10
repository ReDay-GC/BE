package ReDay.admin.application.dto.response;

import java.time.LocalDateTime;

public record AdminAiLogResponse(
        Long logId,
        Long memoryId,
        Long userId,
        String userName,
        String status,
        double responseTimeSec,
        LocalDateTime processedAt
) {
}
