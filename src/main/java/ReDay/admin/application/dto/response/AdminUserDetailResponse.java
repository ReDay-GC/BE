package ReDay.admin.application.dto.response;

import java.time.LocalDateTime;
import java.util.List;

public record AdminUserDetailResponse(
        Long userId,
        String name,
        String email,
        LocalDateTime createdAt,
        long totalMemories,
        long totalRecords,
        long photoCount,
        long textCount,
        long voiceCount,
        List<AdminUserMemoryResponse> memories
) {
}
