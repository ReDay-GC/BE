package ReDay.memory.application.usecase;

import ReDay.memory.application.dto.response.MemoryAnalysisResponse;
import ReDay.memory.application.dto.response.MemoryDetailResponse;
import ReDay.memory.application.mapper.MemoryAnalysisMapper;
import ReDay.memory.application.mapper.MemoryMapper;
import ReDay.memory.domain.entity.Memory;
import ReDay.memory.domain.entity.MemoryAnalysis;
import ReDay.memory.domain.service.MemoryAnalysisService;
import ReDay.memory.domain.service.MemoryGetService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetMemoryDetailUseCase {

    private final MemoryGetService memoryGetService;
    private final MemoryAnalysisService memoryAnalysisService;

    public MemoryDetailResponse execute(Long memoryId) {
        Memory memory = memoryGetService.getMemory(memoryId);

        MemoryAnalysis analysis = memoryAnalysisService.getAnalysis(memoryId);

        MemoryAnalysisResponse analysisResponse =
                analysis != null ? MemoryAnalysisMapper.toResponse(analysis) : null;

        return MemoryMapper.toMemoryDetailResponse(
                memory,
                null,
                null,
                analysisResponse
        );
    }
}
