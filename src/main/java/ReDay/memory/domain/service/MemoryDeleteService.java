package ReDay.memory.domain.service;

import ReDay.memory.application.exception.MemoryNotFoundException;
import ReDay.memory.domain.entity.Memory;
import ReDay.memory.domain.repository.MemoryPersonRepository;
import ReDay.memory.domain.repository.MemoryRecordMappingRepository;
import ReDay.memory.domain.repository.MemoryRepository;
import ReDay.memory.domain.repository.MemoryTagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemoryDeleteService {

    private final MemoryRepository memoryRepository;
    private final MemoryTagRepository memoryTagRepository;
    private final MemoryPersonRepository memoryPersonRepository;
    private final MemoryRecordMappingRepository memoryRecordMappingRepository;

    public void delete(Long memoryId) {
        Memory memory = memoryRepository.findById(memoryId)
                .orElseThrow(MemoryNotFoundException::new);

        memoryTagRepository.deleteAllByMemory(memory);
        memoryPersonRepository.deleteAllByMemory(memory);
        memoryRecordMappingRepository.deleteAllByMemory(memory);
        memoryRepository.delete(memory);
    }
}
