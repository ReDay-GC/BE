
package ReDay.memory.application.usecase;

import ReDay.memory.application.dto.response.MemoryAnalysisResponse;
import ReDay.memory.application.mapper.MemoryAnalysisMapper;
import ReDay.memory.domain.entity.MemoryAnalysis;
import ReDay.memory.domain.service.MemoryAnalysisService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetMemoryAnalysisUseCase {

    private final MemoryAnalysisService memoryAnalysisService;

    public MemoryAnalysisResponse execute(Long memoryId) {
        MemoryAnalysis analysis = memoryAnalysisService.getAnalysis(memoryId);

        if (analysis == null) {
            return null;
        }

        return MemoryAnalysisMapper.toResponse(analysis);
    }
}
