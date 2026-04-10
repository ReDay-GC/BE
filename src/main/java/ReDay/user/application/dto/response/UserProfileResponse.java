package ReDay.user.application.dto.response;

import java.time.LocalDateTime;

public record UserProfileResponse(
        Long userId,
        String name,
        String email,
        LocalDateTime createdAt
) {
}
