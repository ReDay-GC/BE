package ReDay.memory.domain.service;

import ReDay.memory.domain.entity.Memory;
import ReDay.memory.domain.entity.MemoryTag;
import ReDay.memory.domain.repository.MemoryTagRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemoryTagGetService {

    private final MemoryTagRepository memoryTagRepository;

    public List<String> getAllTagNames() {
        return memoryTagRepository.findAllDistinctTagNames();
    }

    public List<Memory> getMemoriesByTagName(String tagName) {
        return memoryTagRepository.findAllByTagName(tagName)
                .stream()
                .map(MemoryTag::getMemory)
                .distinct()
                .toList();
    }
}
