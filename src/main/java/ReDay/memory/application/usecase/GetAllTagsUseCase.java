package ReDay.memory.application.usecase;

import ReDay.memory.application.dto.response.MemoryTagListResponse;
import ReDay.memory.domain.service.MemoryTagGetService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetAllTagsUseCase {

    private final MemoryTagGetService memoryTagGetService;

    public MemoryTagListResponse execute() {
        return new MemoryTagListResponse(memoryTagGetService.getAllTagNames());
    }
}
