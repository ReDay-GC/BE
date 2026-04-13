package ReDay.admin.application.dto.response;

import java.time.LocalDateTime;

public record AdminNoticeListResponse(
        Long noticeId,
        String title,
        boolean isPublic,
        LocalDateTime createdAt
) {
}
