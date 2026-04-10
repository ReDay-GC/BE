package ReDay.memory.domain.service;

import ReDay.memory.application.exception.MemoryAnalysisFailedException;
import ReDay.memory.domain.entity.AiProcessingLog;
import ReDay.memory.domain.entity.MemoryAnalysis;
import ReDay.memory.domain.repository.AiProcessingLogRepository;
import ReDay.memory.domain.repository.MemoryAnalysisRepository;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemoryAnalysisService {

    private final MemoryAnalysisRepository memoryAnalysisRepository;
    private final AiProcessingLogRepository aiProcessingLogRepository;

    @Transactional(readOnly = true)
    public MemoryAnalysis getAnalysis(Long memoryId) {
        return memoryAnalysisRepository.findByMemoryId(memoryId)
                .orElse(null);
    }

    @Transactional
    public MemoryAnalysis analyze(Long memoryId, Long userId) {
        long startTime = System.currentTimeMillis();
        String status = "SUCCESS";

        try {
            MemoryAnalysis analysis = MemoryAnalysis.builder()
                    .memoryId(memoryId)
                    .emotionResult("NEUTRAL")
                    .keywords(java.util.List.of("일상", "기록"))
                    .placeSummary("특정 장소 없음")
                    .activitySummary("일상적인 활동")
                    .overallSummary("하루의 기록이 정리되었습니다.")
                    .build();

            MemoryAnalysis saved = memoryAnalysisRepository.save(analysis);

            long responseTimeMs = System.currentTimeMillis() - startTime;
            saveLog(memoryId, userId, status, responseTimeMs);

            return saved;
        } catch (Exception e) {
            long responseTimeMs = System.currentTimeMillis() - startTime;
            saveLog(memoryId, userId, "FAILED", responseTimeMs);
            throw new MemoryAnalysisFailedException();
        }
    }

    private void saveLog(Long memoryId, Long userId, String status, Long responseTimeMs) {
        aiProcessingLogRepository.save(AiProcessingLog.builder()
                .memoryId(memoryId)
                .userId(userId)
                .status(status)
                .responseTimeMs(responseTimeMs)
                .processedAt(LocalDateTime.now())
                .build());
    }
}
