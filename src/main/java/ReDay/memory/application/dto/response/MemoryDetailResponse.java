package ReDay.memory.application.dto.response;

import java.time.LocalDate;
import java.util.List;

public record MemoryDetailResponse(
        Long memoryId,
        String title,
        String summary,
        String description,
        LocalDate memoryDate,
        String emotion,
        String thumbnailUrl,
        boolean archived,
        List<MemoryTagResponse> tags,
        List<MemoryRecordResponse> records,
        MemoryAnalysisResponse analysis
) {
}
