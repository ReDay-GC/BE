package ReDay.admin.application.dto.response;

import ReDay.inquiry.domain.entity.InquiryStatus;
import java.time.LocalDateTime;

public record AdminInquiryListResponse(
        Long inquiryId,
        Long userId,
        String userName,
        String title,
        InquiryStatus status,
        LocalDateTime createdAt
) {
}
