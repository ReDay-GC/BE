package ReDay.memory.domain.service;

import ReDay.memory.application.exception.MemoryNotFoundException;
import ReDay.memory.domain.entity.Memory;
import ReDay.memory.domain.repository.MemoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemoryDeleteService {

    private final MemoryRepository memoryRepository;

    public void delete(Long memoryId) {
        Memory memory = memoryRepository.findById(memoryId)
                .orElseThrow(MemoryNotFoundException::new);

        memoryRepository.delete(memory);
    }
}
