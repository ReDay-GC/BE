package ReDay.memory.application.usecase;

import ReDay.memory.application.dto.request.MemorySearchRequest;
import ReDay.memory.application.dto.response.MemorySearchResponse;
import ReDay.memory.application.mapper.MemoryMapper;
import ReDay.memory.domain.service.MemoryGetService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SearchMemoryUseCase {

    private final MemoryGetService memoryGetService;

    public List<MemorySearchResponse> execute(MemorySearchRequest request) {
        return memoryGetService.searchMemoryByKeyword(request.keyword())
                .stream()
                .map(MemoryMapper::toMemorySearchResponse)
                .toList();
    }
}
