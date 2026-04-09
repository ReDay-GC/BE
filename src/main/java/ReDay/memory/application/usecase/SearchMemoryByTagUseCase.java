package ReDay.memory.application.usecase;

import ReDay.memory.application.dto.response.MemorySearchResponse;
import ReDay.memory.application.mapper.MemoryMapper;
import ReDay.memory.domain.service.MemoryTagGetService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SearchMemoryByTagUseCase {

    private final MemoryTagGetService memoryTagGetService;

    public List<MemorySearchResponse> execute(String tagName) {
        return memoryTagGetService.getMemoriesByTagName(tagName)
                .stream()
                .map(MemoryMapper::toMemorySearchResponse)
                .toList();
    }
}
