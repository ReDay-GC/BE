package ReDay.memory.application.mapper;

import ReDay.memory.application.dto.response.MemoryAnalysisResponse;
import ReDay.memory.application.dto.response.MemoryDetailResponse;
import ReDay.memory.application.dto.response.MemoryListResponse;
import ReDay.memory.application.dto.response.MemorySearchResponse;
import ReDay.memory.domain.entity.Memory;
import java.util.List;

public class MemoryMapper {

    private MemoryMapper() {
    }

    public static MemoryDetailResponse toMemoryDetailResponse(
            Memory memory,
            List<?> tags,
            List<?> records,
            MemoryAnalysisResponse analysis
    ) {
        return new MemoryDetailResponse(
                memory.getId(),
                memory.getTitle(),
                memory.getSummary(),
                memory.getDescription(),
                memory.getMemoryDate(),
                memory.getEmotion(),
                memory.getThumbnailUrl(),
                memory.getLocation(),
                memory.isArchived(),
                List.of(),
                List.of(),
                analysis
        );
    }

    public static MemoryListResponse toMemoryListResponse(Memory memory) {
        return new MemoryListResponse(
                memory.getId(),
                memory.getTitle(),
                memory.getSummary(),
                memory.getMemoryDate(),
                memory.getEmotion(),
                memory.getThumbnailUrl(),
                memory.getLocation()
        );
    }

    public static MemorySearchResponse toMemorySearchResponse(Memory memory) {
        return new MemorySearchResponse(
                memory.getId(),
                memory.getTitle(),
                memory.getSummary(),
                memory.getMemoryDate(),
                memory.getEmotion(),
                memory.getThumbnailUrl(),
                memory.getLocation()
        );
    }
}
