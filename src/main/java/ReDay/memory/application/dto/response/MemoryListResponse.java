package ReDay.memory.application.dto.response;

import java.time.LocalDate;
import java.util.List;

public record MemoryListResponse(
        Long memoryId,
        String title,
        String summary,
        LocalDate memoryDate,
        String emotion,
        String thumbnailUrl,
        String location,
        long recordCount,
        List<String> tags,
        List<String> people
) {
}
