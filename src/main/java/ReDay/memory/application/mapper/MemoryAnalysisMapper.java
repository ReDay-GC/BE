package ReDay.memory.application.mapper;

import ReDay.memory.application.dto.response.MemoryAnalysisResponse;
import ReDay.memory.domain.entity.MemoryAnalysis;

public class MemoryAnalysisMapper {

    private MemoryAnalysisMapper() {
    }

    public static MemoryAnalysisResponse toResponse(MemoryAnalysis analysis) {
        return new MemoryAnalysisResponse(
                analysis.getId(),
                analysis.getEmotionResult(),
                analysis.getKeywords(),
                analysis.getPlaceSummary(),
                analysis.getActivitySummary(),
                analysis.getOverallSummary(),
                analysis.getAnalyzedAt()
        );
    }
}
