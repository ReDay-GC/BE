package ReDay.memory.domain.service;

import ReDay.memory.application.dto.request.MemorySaveRequest;
import ReDay.memory.domain.entity.Memory;
import ReDay.memory.domain.entity.MemoryPerson;
import ReDay.memory.domain.entity.MemoryRecordMapping;
import ReDay.memory.domain.entity.MemoryTag;
import ReDay.memory.domain.repository.MemoryPersonRepository;
import ReDay.memory.domain.repository.MemoryRecordMappingRepository;
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
    private final MemoryRecordMappingRepository memoryRecordMappingRepository;

    public Memory save(Long userId, MemorySaveRequest request) {
        Memory memory = Memory.builder()
                .userId(userId)
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

        if (request.recordIds() != null) {
            List<MemoryRecordMapping> mappings = request.recordIds().stream()
                    .map(recordId -> MemoryRecordMapping.builder().memory(memory).recordId(recordId).build())
                    .toList();
            memoryRecordMappingRepository.saveAll(mappings);
        }

        return memory;
    }
}
