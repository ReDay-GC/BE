package ReDay.memory.application.usecase;

import ReDay.memory.application.dto.response.MemoryListResponse;
import ReDay.memory.application.mapper.MemoryMapper;
import ReDay.memory.domain.service.MemoryGetService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetMemoryByLocationUseCase {

    private final MemoryGetService memoryGetService;

    public List<MemoryListResponse> execute(String location) {
        return memoryGetService.getMemoriesByLocation(location)
                .stream()
                .map(MemoryMapper::toMemoryListResponse)
                .toList();
    }
}
