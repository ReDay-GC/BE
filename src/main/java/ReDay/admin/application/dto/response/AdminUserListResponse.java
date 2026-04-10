package ReDay.admin.application.dto.response;

import java.time.LocalDateTime;

public record AdminUserListResponse(
        Long userId,
        String name,
        String email,
        LocalDateTime createdAt,
        long memoryCount
) {
}
