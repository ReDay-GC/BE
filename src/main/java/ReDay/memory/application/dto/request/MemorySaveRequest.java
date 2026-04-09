package ReDay.memory.application.dto.request;

import java.time.LocalDate;

public record MemorySaveRequest(
        String title,
        String summary,
        String description,
        LocalDate memoryDate,
        String emotion,
        String thumbnailUrl,
        String location
) {
}
