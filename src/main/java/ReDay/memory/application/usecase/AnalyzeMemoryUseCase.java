package ReDay.memory.application.usecase;

import ReDay.memory.application.dto.response.MemoryAnalysisResponse;
import ReDay.memory.application.mapper.MemoryAnalysisMapper;
import ReDay.memory.domain.entity.MemoryAnalysis;
import ReDay.memory.domain.service.MemoryAnalysisService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AnalyzeMemoryUseCase {

    private final MemoryAnalysisService memoryAnalysisService;

    public MemoryAnalysisResponse execute(Long userId, Long memoryId) {
        MemoryAnalysis analysis = memoryAnalysisService.analyze(memoryId, userId);

        return MemoryAnalysisMapper.toResponse(analysis);
    }
}
