package ReDay.memory.domain.service;

import ReDay.memory.application.exception.MemoryAnalysisFailedException;
import ReDay.memory.domain.entity.MemoryAnalysis;
import ReDay.memory.domain.repository.MemoryAnalysisRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemoryAnalysisService {

    private final MemoryAnalysisRepository memoryAnalysisRepository;

    public MemoryAnalysis getAnalysis(Long memoryId) {
        return memoryAnalysisRepository.findByMemoryId(memoryId)
                .orElse(null);
    }

    public MemoryAnalysis analyze(Long memoryId) {
        try {
            MemoryAnalysis analysis = MemoryAnalysis.builder()
                    .memoryId(memoryId)
                    .emotionResult("NEUTRAL")
                    .keywords(java.util.List.of("일상", "기록"))
                    .placeSummary("특정 장소 없음")
                    .activitySummary("일상적인 활동")
                    .overallSummary("하루의 기록이 정리되었습니다.")
                    .build();

            return memoryAnalysisRepository.save(analysis);
        } catch (Exception e) {
            throw new MemoryAnalysisFailedException();
        }
    }
}
