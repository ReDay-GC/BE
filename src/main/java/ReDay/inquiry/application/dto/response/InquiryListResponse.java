package ReDay.inquiry.application.dto.response;

import ReDay.inquiry.domain.entity.InquiryStatus;
import java.time.LocalDateTime;

public record InquiryListResponse(
        Long inquiryId,
        String title,
        String contentPreview,
        InquiryStatus status,
        String replyContent,
        LocalDateTime createdAt
) {
}
