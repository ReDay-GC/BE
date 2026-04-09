package ReDay.memory.application.dto.response;

import java.time.LocalDate;

public record MemorySearchResponse(
        Long memoryId,
        String title,
        String summary,
        LocalDate memoryDate,
        String emotion,
        String thumbnailUrl,
        String location
) {
}
