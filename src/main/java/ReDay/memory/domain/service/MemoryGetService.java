package ReDay.memory.domain.service;

import ReDay.memory.domain.entity.Memory;
import ReDay.memory.domain.repository.MemoryRepository;
import ReDay.memory.application.exception.MemoryNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemoryGetService {

    private final MemoryRepository memoryRepository;

    public Memory getMemory(Long memoryId) {
        return memoryRepository.findById(memoryId)
                .orElseThrow(MemoryNotFoundException::new);
    }

    public List<Memory> getMemoryList() {
        return memoryRepository.findAllByOrderByMemoryDateDesc();
    }

    public List<Memory> searchMemoryByKeyword(String keyword) {
        return memoryRepository.findAllByTitleContainingIgnoreCaseOrderByMemoryDateDesc(keyword);
    }
}
