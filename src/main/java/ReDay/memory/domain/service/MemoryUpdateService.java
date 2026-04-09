package ReDay.memory.domain.service;

import ReDay.memory.application.dto.request.MemoryUpdateRequest;
import ReDay.memory.application.exception.MemoryNotFoundException;
import ReDay.memory.domain.entity.Memory;
import ReDay.memory.domain.repository.MemoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemoryUpdateService {

    private final MemoryRepository memoryRepository;

    public Memory update(Long memoryId, MemoryUpdateRequest request) {
        Memory memory = memoryRepository.findById(memoryId)
                .orElseThrow(MemoryNotFoundException::new);

        String title = request.title() != null ? request.title() : memory.getTitle();
        String summary = request.summary() != null ? request.summary() : memory.getSummary();
        String description = request.description() != null ? request.description() : memory.getDescription();
        var memoryDate = request.memoryDate() != null ? request.memoryDate() : memory.getMemoryDate();
        String emotion = request.emotion() != null ? request.emotion() : memory.getEmotion();
        String thumbnailUrl = request.thumbnailUrl() != null ? request.thumbnailUrl() : memory.getThumbnailUrl();
        String location = request.location() != null ? request.location() : memory.getLocation();

        memory.update(
                title,
                summary,
                description,
                memoryDate,
                emotion,
                thumbnailUrl,
                location
        );

        return memory;
    }
}
