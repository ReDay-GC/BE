package ReDay.analysis.application.dto.response;

import java.util.List;

public record MonthlyAnalysisResponse(
        String monthlyInsight,
        List<MemoryTrendItem> memoryTrend,
        List<RecordTypeStatItem> recordTypeStats,
        List<ActivityTypeItem> topActivities,
        List<PlaceFrequencyItem> topPlaces,
        List<PersonFrequencyItem> topPeople
) {
}
