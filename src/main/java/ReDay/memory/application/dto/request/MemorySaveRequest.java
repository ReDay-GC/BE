package ReDay.memory.application.dto.request;

import java.time.LocalDate;
import java.util.List;

public record MemorySaveRequest(
        String title,
        String summary,
        String description,
        LocalDate memoryDate,
        String emotion,
        String thumbnailUrl,
        String location,
        List<String> tags,
        List<String> people,
        List<Long> recordIds
) {
}
