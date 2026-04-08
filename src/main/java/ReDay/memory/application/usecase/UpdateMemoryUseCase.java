package ReDay.memory.application.usecase;

import ReDay.memory.application.dto.request.MemoryUpdateRequest;
import ReDay.memory.application.dto.response.MemoryDetailResponse;
import ReDay.memory.application.mapper.MemoryMapper;
import ReDay.memory.domain.entity.Memory;
import ReDay.memory.domain.service.MemoryUpdateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UpdateMemoryUseCase {

    private final MemoryUpdateService memoryUpdateService;

    public MemoryDetailResponse execute(Long memoryId, MemoryUpdateRequest request) {
        Memory memory = memoryUpdateService.update(memoryId, request);

        return MemoryMapper.toMemoryDetailResponse(
                memory,
                null,
                null,
                null
        );
    }
}
