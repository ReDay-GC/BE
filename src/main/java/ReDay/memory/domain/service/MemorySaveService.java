package ReDay.memory.domain.service;

import ReDay.memory.application.dto.request.MemorySaveRequest;
import ReDay.memory.domain.entity.Memory;
import ReDay.memory.domain.repository.MemoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemorySaveService {

    private final MemoryRepository memoryRepository;

    public Memory save(MemorySaveRequest request) {
        Memory memory = Memory.builder()
                .title(request.title())
                .summary(request.summary())
                .description(request.description())
                .memoryDate(request.memoryDate())
                .emotion(request.emotion())
                .thumbnailUrl(request.thumbnailUrl())
                .location(request.location())
                .archived(false)
                .build();

        return memoryRepository.save(memory);
    }
}
