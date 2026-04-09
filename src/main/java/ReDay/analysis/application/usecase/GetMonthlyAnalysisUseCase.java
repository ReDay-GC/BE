package ReDay.analysis.application.usecase;

import ReDay.analysis.application.dto.response.MemoryTrendItem;
import ReDay.analysis.application.dto.response.MonthlyAnalysisResponse;
import ReDay.analysis.application.dto.response.PlaceFrequencyItem;
import ReDay.analysis.application.dto.response.RecordTypeStatItem;
import ReDay.analysis.application.mapper.AnalysisMapper;
import ReDay.analysis.domain.entity.MonthlyInsight;
import ReDay.analysis.domain.service.AnalysisStatGetService;
import ReDay.analysis.domain.service.MonthlyInsightGetService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetMonthlyAnalysisUseCase {

    private final AnalysisStatGetService analysisStatGetService;
    private final MonthlyInsightGetService monthlyInsightGetService;

    public MonthlyAnalysisResponse execute(Long userId, int year, int month) {
        List<MemoryTrendItem> trend = analysisStatGetService.getMemoryTrend(userId);
        List<RecordTypeStatItem> recordTypeStats = analysisStatGetService.getRecordTypeStats(userId, year, month);
        List<PlaceFrequencyItem> topPlaces = analysisStatGetService.getTopPlaces(userId, year, month);
        MonthlyInsight insight = monthlyInsightGetService.findInsight(userId, year, month).orElse(null);

        return AnalysisMapper.toMonthlyAnalysisResponse(trend, recordTypeStats, topPlaces, insight);
    }
}
