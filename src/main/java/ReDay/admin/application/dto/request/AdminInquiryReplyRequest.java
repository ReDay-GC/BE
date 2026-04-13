package ReDay.admin.application.dto.request;

import jakarta.validation.constraints.NotBlank;

public record AdminInquiryReplyRequest(
        @NotBlank String content
) {
}
