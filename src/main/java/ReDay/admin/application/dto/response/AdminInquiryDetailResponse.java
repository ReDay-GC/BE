package ReDay.admin.application.dto.response;

import ReDay.inquiry.domain.entity.InquiryStatus;
import java.time.LocalDateTime;

public record AdminInquiryDetailResponse(
        Long inquiryId,
        Long userId,
        String userName,
        String title,
        String content,
        InquiryStatus status,
        String replyContent,
        LocalDateTime repliedAt,
        LocalDateTime createdAt
) {
}
