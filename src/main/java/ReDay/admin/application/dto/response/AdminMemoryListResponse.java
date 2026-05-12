package ReDay.admin.application.dto.response;

import ReDay.memory.domain.entity.Memory;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record AdminMemoryListResponse(
        Long memoryId,
        Long userId,
        String title,
        String summary,
        LocalDate memoryDate,
        String emotion,
        String thumbnailUrl,
        LocalDateTime createdAt
) {
    public static AdminMemoryListResponse from(Memory memory) {
        return new AdminMemoryListResponse(
                memory.getId(),
                memory.getUserId(),
                memory.getTitle(),
                memory.getSummary(),
                memory.getMemoryDate(),
                memory.getEmotion(),
                memory.getThumbnailUrl(),
                memory.getCreatedAt()
        );
    }
}
