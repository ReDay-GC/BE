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

    public List<String> getAllTagNames(Long userId) {
        return memoryTagRepository.findAllDistinctTagNamesByUserId(userId);
    }

    public List<Memory> getMemoriesByTagName(Long userId, String tagName) {
        return memoryTagRepository.findAllByTagNameAndUserId(tagName, userId)
                .stream()
                .map(MemoryTag::getMemory)
                .distinct()
                .toList();
    }
}
