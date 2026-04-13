package ReDay.analysis.domain.service;

import ReDay.analysis.domain.entity.ActivityEntry;
import ReDay.analysis.domain.entity.MonthlyInsight;
import ReDay.analysis.domain.entity.PersonEntry;
import ReDay.analysis.domain.repository.MonthlyInsightRepository;
import ReDay.analysis.infrastructure.AnalysisAiService;
import ReDay.analysis.infrastructure.AnalysisAiService.AiInsightResult;
import ReDay.record.domain.entity.Record;
import ReDay.record.domain.repository.RecordRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MonthlyInsightSaveService {

    private final MonthlyInsightRepository monthlyInsightRepository;
    private final RecordRepository recordRepository;
    private final AnalysisAiService analysisAiService;

    @Transactional
    public MonthlyInsight generateAndSave(Long userId, int year, int month) {
        monthlyInsightRepository.findByUserIdAndYearAndMonth(userId, year, month)
                .ifPresent(monthlyInsightRepository::delete);

        LocalDate startDate = LocalDate.of(year, month, 1);
        LocalDate endDate = startDate.withDayOfMonth(startDate.lengthOfMonth());
        List<Record> records = recordRepository.findByUserIdAndRecordDateBetween(userId, startDate, endDate);

        String recordsText = records.stream()
                .filter(r -> r.getTextContent() != null && !r.getTextContent().isBlank())
                .map(r -> "- " + r.getRecordDate() + ": " + r.getTextContent())
                .collect(Collectors.joining("\n"));

        AiInsightResult result = analysisAiService.generateInsight(year, month, recordsText);

        List<PersonEntry> topPeople = result.topPeople().stream()
                .map(p -> new PersonEntry(p.name(), p.count()))
                .collect(Collectors.toList());

        List<ActivityEntry> topActivities = result.topActivities().stream()
                .map(a -> new ActivityEntry(a.activityType(), a.percentage()))
                .collect(Collectors.toList());

        MonthlyInsight insight = MonthlyInsight.builder()
                .userId(userId)
                .year(year)
                .month(month)
                .insightText(result.insight())
                .topPeople(topPeople)
                .topActivities(topActivities)
                .build();

        return monthlyInsightRepository.save(insight);
    }

    @Transactional
    public void saveFromExternal(Long userId, int year, int month, String insightText,
                                  List<PersonEntry> topPeople, List<ActivityEntry> topActivities) {
        monthlyInsightRepository.findByUserIdAndYearAndMonth(userId, year, month)
                .ifPresent(monthlyInsightRepository::delete);

        MonthlyInsight insight = MonthlyInsight.builder()
                .userId(userId)
                .year(year)
                .month(month)
                .insightText(insightText)
                .topPeople(topPeople)
                .topActivities(topActivities)
                .build();

        monthlyInsightRepository.save(insight);
    }
}
