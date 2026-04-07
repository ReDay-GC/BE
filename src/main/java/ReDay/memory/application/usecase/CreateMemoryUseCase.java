package ReDay.memory.application.usecase;

import ReDay.memory.application.dto.request.MemorySaveRequest;
import ReDay.memory.application.dto.response.MemoryListResponse;
import ReDay.memory.application.mapper.MemoryMapper;
import ReDay.memory.domain.entity.Memory;
import ReDay.memory.domain.service.MemorySaveService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateMemoryUseCase {

    private final MemorySaveService memorySaveService;

    public MemoryListResponse execute(MemorySaveRequest request) {
        Memory memory = memorySaveService.save(request);

        return MemoryMapper.toMemoryListResponse(memory);
    }
}
