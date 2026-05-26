package ReDay.memory.domain.service;

import ReDay.memory.application.exception.MemoryAnalysisFailedException;
import ReDay.memory.domain.entity.AiProcessingLog;
import ReDay.memory.domain.entity.Memory;
import ReDay.memory.domain.entity.MemoryAnalysis;
import ReDay.memory.domain.repository.AiProcessingLogRepository;
import ReDay.memory.domain.repository.MemoryAnalysisRepository;
import ReDay.memory.domain.repository.MemoryRepository;
import ReDay.memory.infrastructure.AiMemoryAnalysisClient;
import ReDay.memory.infrastructure.AiMemoryAnalysisClient.AnalysisResult;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemoryAnalysisService {

    private final MemoryAnalysisRepository memoryAnalysisRepository;
    private final AiProcessingLogRepository aiProcessingLogRepository;
    private final MemoryRepository memoryRepository;
    private final AiMemoryAnalysisClient aiMemoryAnalysisClient;

    @Transactional(readOnly = true)
    public MemoryAnalysis getAnalysis(Long memoryId) {
        return memoryAnalysisRepository.findByMemoryId(memoryId)
                .orElse(null);
    }

    @Transactional
    public MemoryAnalysis analyze(Long memoryId, Long userId) {
        long startTime = System.currentTimeMillis();

        try {
            Memory memory = memoryRepository.findById(memoryId)
                    .orElseThrow(MemoryAnalysisFailedException::new);

            String text = buildText(memory);

            AnalysisResult result = aiMemoryAnalysisClient.analyzeText(text);
            List<String> keywords = aiMemoryAnalysisClient.extractKeywords(text);

            MemoryAnalysis analysis = MemoryAnalysis.builder()
                    .memoryId(memoryId)
                    .emotionResult(result.emotion())
                    .keywords(keywords)
                    .placeSummary(memory.getLocation() != null ? memory.getLocation() : "장소 정보 없음")
                    .activitySummary(result.activityHint())
                    .overallSummary(result.summary())
                    .build();

            MemoryAnalysis saved = memoryAnalysisRepository.save(analysis);

            saveLog(memoryId, userId, "SUCCESS", System.currentTimeMillis() - startTime);
            return saved;

        } catch (MemoryAnalysisFailedException e) {
            saveLog(memoryId, userId, "FAILED", System.currentTimeMillis() - startTime);
            throw e;
        } catch (Exception e) {
            saveLog(memoryId, userId, "FAILED", System.currentTimeMillis() - startTime);
            throw new MemoryAnalysisFailedException();
        }
    }

    private String buildText(Memory memory) {
        StringBuilder sb = new StringBuilder();
        sb.append(memory.getTitle()).append("\n");
        sb.append(memory.getSummary());
        if (memory.getDescription() != null && !memory.getDescription().isBlank()) {
            sb.append("\n").append(memory.getDescription());
        }
        return sb.toString();
    }

    private void saveLog(Long memoryId, Long userId, String status, long responseTimeMs) {
        aiProcessingLogRepository.save(AiProcessingLog.builder()
                .memoryId(memoryId)
                .userId(userId)
                .status(status)
                .responseTimeMs(responseTimeMs)
                .processedAt(LocalDateTime.now())
                .build());
    }
}
