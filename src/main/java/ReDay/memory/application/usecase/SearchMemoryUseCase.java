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
import java.util.stream.Stream;
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

        List<Map<String, Object>> memoryData = allMemories.stream()
                .map(m -> {
                    List<String> tags = memoryTagRepository.findAllByMemory(m).stream()
                            .map(t -> t.getTagName()).toList();
                    List<String> people = memoryPersonRepository.findAllByMemory(m).stream()
                            .map(p -> p.getPersonName()).toList();

                    String text = Stream.of(
                            m.getTitle(),
                            m.getSummary(),
                            m.getDescription(),
                            translateEmotion(m.getEmotion()),
                            m.getLocation(),
                            String.join(" ", tags),
                            String.join(" ", people)
                    ).filter(s -> s != null && !s.isBlank()).collect(Collectors.joining(" "));

                    return Map.<String, Object>of("memory_id", m.getId(), "text", text);
                })
                .toList();

        List<Long> rankedIds = aiSemanticSearchClient.searchSemantic(request.keyword(), memoryData);

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

    private String translateEmotion(String emotion) {
        if (emotion == null) return null;
        return switch (emotion) {
            case "HAPPY" -> "행복 행복한 행복하다 즐거움 즐거운 즐겁다";
            case "EXCITED" -> "신나는 신나다 신나게 설렘 설레는 설레다";
            case "SAD" -> "슬픔 슬픈 슬프다 힘듦 힘든 힘들다 지침 지친 지치다";
            case "CONTENT" -> "평온 평온한 평온하다 편안 편안한";
            case "ANGRY" -> "화난 화나다 짜증 짜증나는 분노 분노한 분노하다";
            case "ANXIOUS" -> "불안 불안한 불안하다 걱정 걱정되는";
            default -> emotion;
        };
    }
}
