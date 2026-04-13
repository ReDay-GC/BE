package ReDay.admin.application.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AdminNoticeSaveRequest(
        @NotBlank String title,
        @NotBlank String content,
        @NotNull Boolean isPublic
) {
}
