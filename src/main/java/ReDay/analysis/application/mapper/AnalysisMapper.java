package ReDay.analysis.application.mapper;

import ReDay.analysis.application.dto.response.ActivityTypeItem;
import ReDay.analysis.application.dto.response.MemoryTrendItem;
import ReDay.analysis.application.dto.response.MonthlyAnalysisResponse;
import ReDay.analysis.application.dto.response.PersonFrequencyItem;
import ReDay.analysis.application.dto.response.PlaceFrequencyItem;
import ReDay.analysis.application.dto.response.RecordTypeStatItem;
import ReDay.analysis.domain.entity.MonthlyInsight;
import java.util.ArrayList;
import java.util.List;

public class AnalysisMapper {

    private AnalysisMapper() {
    }

    public static MonthlyAnalysisResponse toMonthlyAnalysisResponse(
            List<MemoryTrendItem> trend,
            List<RecordTypeStatItem> recordTypeStats,
            List<PlaceFrequencyItem> topPlaces,
            MonthlyInsight insight) {

        if (insight == null) {
            return new MonthlyAnalysisResponse(null, trend, recordTypeStats, List.of(), topPlaces, List.of());
        }

        List<ActivityTypeItem> activities = insight.getTopActivities().stream()
                .map(a -> new ActivityTypeItem(a.getActivityType(), a.getPercentage()))
                .toList();

        List<PersonFrequencyItem> people = new ArrayList<>();
        for (int i = 0; i < insight.getTopPeople().size(); i++) {
            var p = insight.getTopPeople().get(i);
            people.add(new PersonFrequencyItem(i + 1, p.getName(), p.getCount()));
        }

        return new MonthlyAnalysisResponse(insight.getInsightText(), trend, recordTypeStats, activities, topPlaces, people);
    }
}
