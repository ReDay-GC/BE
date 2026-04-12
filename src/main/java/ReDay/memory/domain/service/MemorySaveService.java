package ReDay.memory.domain.service;

import ReDay.memory.application.dto.request.MemorySaveRequest;
import ReDay.memory.domain.entity.Memory;
import ReDay.memory.domain.entity.MemoryPerson;
import ReDay.memory.domain.entity.MemoryTag;
import ReDay.memory.domain.repository.MemoryPersonRepository;
import ReDay.memory.domain.repository.MemoryRepository;
import ReDay.memory.domain.repository.MemoryTagRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemorySaveService {

    private final MemoryRepository memoryRepository;
    private final MemoryTagRepository memoryTagRepository;
    private final MemoryPersonRepository memoryPersonRepository;

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

        memoryRepository.save(memory);

        if (request.tags() != null) {
            List<MemoryTag> tags = request.tags().stream()
                    .map(tagName -> MemoryTag.builder().tagName(tagName).memory(memory).build())
                    .toList();
            memoryTagRepository.saveAll(tags);
        }

        if (request.people() != null) {
            List<MemoryPerson> people = request.people().stream()
                    .map(name -> MemoryPerson.builder().personName(name).memory(memory).build())
                    .toList();
            memoryPersonRepository.saveAll(people);
        }

        return memory;
    }
}
