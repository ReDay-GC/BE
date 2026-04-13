package ReDay.admin.application.dto.request;

import ReDay.inquiry.domain.entity.InquiryStatus;
import jakarta.validation.constraints.NotNull;

public record AdminInquiryStatusRequest(
        @NotNull InquiryStatus status
) {
}
