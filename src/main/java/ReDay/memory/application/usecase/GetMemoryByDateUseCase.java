package ReDay.memory.application.usecase;

import ReDay.memory.application.dto.response.MemoryListResponse;
import ReDay.memory.application.mapper.MemoryMapper;
import ReDay.memory.domain.repository.MemoryPersonRepository;
import ReDay.memory.domain.repository.MemoryRecordMappingRepository;
import ReDay.memory.domain.repository.MemoryTagRepository;
import ReDay.memory.domain.service.MemoryGetService;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetMemoryByDateUseCase {

    private final MemoryGetService memoryGetService;
    private final MemoryRecordMappingRepository memoryRecordMappingRepository;
    private final MemoryTagRepository memoryTagRepository;
    private final MemoryPersonRepository memoryPersonRepository;

    public List<MemoryListResponse> execute(LocalDate date) {
        return memoryGetService.getMemoriesByDate(date)
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
