package ReDay.memory.application.usecase;

import ReDay.memory.application.dto.request.MemorySearchRequest;
import ReDay.memory.application.dto.response.MemorySearchResponse;
import ReDay.memory.application.mapper.MemoryMapper;
import ReDay.memory.domain.entity.Memory;
import ReDay.memory.domain.repository.MemoryRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SearchMemoryUseCase {

    private final MemoryRepository memoryRepository;

    public List<MemorySearchResponse> execute(MemorySearchRequest request) {
        List<Memory> memories;

        if (request.keyword() != null && !request.keyword().isBlank()) {
            memories = memoryRepository.findAllByTitleContainingIgnoreCaseOrderByMemoryDateDesc(request.keyword());
        } else if (request.emotion() != null && !request.emotion().isBlank()) {
            memories = memoryRepository.findAllByEmotionOrderByMemoryDateDesc(request.emotion());
        } else if (request.startDate() != null && request.endDate() != null) {
            memories = memoryRepository.findAllByMemoryDateBetweenOrderByMemoryDateDesc(
                    request.startDate(),
                    request.endDate()
            );
        } else {
            memories = memoryRepository.findAllByOrderByMemoryDateDesc();
        }

        return memories.stream()
                .map(MemoryMapper::toMemorySearchResponse)
                .toList();
    }
}
