package ReDay.memory.application.usecase;

import ReDay.memory.application.dto.request.MemorySearchRequest;
import ReDay.memory.application.dto.response.MemoryListResponse;
import ReDay.memory.application.mapper.MemoryMapper;
import ReDay.memory.domain.entity.Memory;
import ReDay.memory.domain.repository.MemoryPersonRepository;
import ReDay.memory.domain.repository.MemoryRecordMappingRepository;
import ReDay.memory.domain.repository.MemoryTagRepository;
import ReDay.memory.domain.service.MemoryGetService;
import ReDay.memory.infrastructure.AiSemanticSearchClient;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class SearchMemoryUseCase {

    private final MemoryGetService memoryGetService;
    private final AiSemanticSearchClient aiSemanticSearchClient;
    private final MemoryRecordMappingRepository memoryRecordMappingRepository;
    private final MemoryTagRepository memoryTagRepository;
    private final MemoryPersonRepository memoryPersonRepository;

    public List<MemoryListResponse> execute(Long userId, MemorySearchRequest request) {
        List<Memory> allMemories = memoryGetService.getMemoryList(userId);

        List<Long> memoryIds = allMemories.stream().map(Memory::getId).toList();
        List<Long> rankedIds = aiSemanticSearchClient.searchSemantic(request.keyword(), memoryIds);

        List<Memory> orderedMemories = rankedIds.isEmpty()
                ? fallbackToKeywordSearch(userId, request.keyword())
                : sortByRankedIds(allMemories, rankedIds);

        return orderedMemories.stream()
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

    private List<Memory> sortByRankedIds(List<Memory> memories, List<Long> rankedIds) {
        Map<Long, Memory> memoryById = memories.stream()
                .collect(Collectors.toMap(Memory::getId, Function.identity()));

        return rankedIds.stream()
                .filter(memoryById::containsKey)
                .map(memoryById::get)
                .toList();
    }

    private List<Memory> fallbackToKeywordSearch(Long userId, String keyword) {
        log.warn("[SearchMemoryUseCase] AI 시맨틱 검색 실패 — 키워드 검색으로 폴백: keyword={}", keyword);
        return memoryGetService.searchMemoryByKeyword(userId, keyword);
    }
}
