package ReDay.inquiry.application.dto.response;

import ReDay.inquiry.domain.entity.InquiryStatus;
import java.time.LocalDateTime;

public record InquiryDetailResponse(
        Long inquiryId,
        String title,
        String content,
        InquiryStatus status,
        String replyContent,
        LocalDateTime repliedAt,
        LocalDateTime createdAt
) {
}
