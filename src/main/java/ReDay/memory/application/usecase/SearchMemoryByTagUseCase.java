package ReDay.memory.application.usecase;

import ReDay.memory.application.dto.response.MemoryListResponse;
import ReDay.memory.application.mapper.MemoryMapper;
import ReDay.memory.domain.repository.MemoryPersonRepository;
import ReDay.memory.domain.repository.MemoryRecordMappingRepository;
import ReDay.memory.domain.repository.MemoryTagRepository;
import ReDay.memory.domain.service.MemoryTagGetService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SearchMemoryByTagUseCase {

    private final MemoryTagGetService memoryTagGetService;
    private final MemoryRecordMappingRepository memoryRecordMappingRepository;
    private final MemoryTagRepository memoryTagRepository;
    private final MemoryPersonRepository memoryPersonRepository;

    public List<MemoryListResponse> execute(Long userId, String tagName) {
        return memoryTagGetService.getMemoriesByTagName(userId, tagName)
                .stream()
                .map(memory -> MemoryMapper.toMemoryListResponse(
                        memory,
                        memoryRecordMappingRepository.countByMemoryId(memory.getId()),
                        memoryTagRepository.findAllByMemory(memory).stream()
                                .map(t -> t.getTagName()).toList(),
                        memoryPersonRepository.findAllByMemory(memory).stream()
                                .map(p -> p.getPersonName()).toList()
                ))
                .toList();
    }
}
