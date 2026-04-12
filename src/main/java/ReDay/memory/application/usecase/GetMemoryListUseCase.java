package ReDay.memory.application.usecase;

import ReDay.memory.application.dto.response.MemoryListResponse;
import ReDay.memory.application.mapper.MemoryMapper;
import ReDay.memory.domain.entity.Memory;
import ReDay.memory.domain.repository.MemoryRecordMappingRepository;
import ReDay.memory.domain.service.MemoryGetService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetMemoryListUseCase {

    private final MemoryGetService memoryGetService;
    private final MemoryRecordMappingRepository memoryRecordMappingRepository;

    public List<MemoryListResponse> execute() {
        List<Memory> memories = memoryGetService.getMemoryList();

        return memories.stream()
                .map(memory -> MemoryMapper.toMemoryListResponse(
                        memory,
                        memoryRecordMappingRepository.countByMemoryId(memory.getId())
                ))
                .toList();
    }
}
