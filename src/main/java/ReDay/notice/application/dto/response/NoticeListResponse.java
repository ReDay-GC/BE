package ReDay.notice.application.dto.response;

import java.time.LocalDateTime;

public record NoticeListResponse(
        Long noticeId,
        String title,
        String contentPreview,
        boolean isNew,
        LocalDateTime createdAt
) {
}
