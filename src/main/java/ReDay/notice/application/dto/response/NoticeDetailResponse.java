package ReDay.notice.application.dto.response;

import java.time.LocalDateTime;

public record NoticeDetailResponse(
        Long noticeId,
        String title,
        String content,
        LocalDateTime createdAt
) {
}
