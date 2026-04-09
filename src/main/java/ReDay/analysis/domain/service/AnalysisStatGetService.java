package ReDay.analysis.domain.service;

import ReDay.analysis.application.dto.response.MemoryTrendItem;
import ReDay.analysis.application.dto.response.PlaceFrequencyItem;
import ReDay.analysis.application.dto.response.RecordTypeStatItem;
import ReDay.record.domain.entity.Record;
import ReDay.record.domain.repository.RecordRepository;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AnalysisStatGetService {

    private static final int TREND_MONTHS = 6;
    private static final int TOP_PLACES = 5;

    private final RecordRepository recordRepository;

    public List<MemoryTrendItem> getMemoryTrend(Long userId) {
        LocalDate startDate = LocalDate.now().minusMonths(TREND_MONTHS - 1).withDayOfMonth(1);
        List<Record> records = recordRepository.findByUserIdAndRecordDateGreaterThanEqual(userId, startDate);

        Map<String, Long> countByMonth = records.stream()
                .collect(Collectors.groupingBy(
                        r -> r.getRecordDate().getYear() + "-" + r.getRecordDate().getMonthValue(),
                        Collectors.counting()
                ));

        List<MemoryTrendItem> trend = new ArrayList<>();
        for (int i = TREND_MONTHS - 1; i >= 0; i--) {
            LocalDate target = LocalDate.now().minusMonths(i);
            String key = target.getYear() + "-" + target.getMonthValue();
            trend.add(new MemoryTrendItem(target.getYear(), target.getMonthValue(),
                    countByMonth.getOrDefault(key, 0L)));
        }
        return trend;
    }

    public List<RecordTypeStatItem> getRecordTypeStats(Long userId, int year, int month) {
        List<Record> records = getRecordsForMonth(userId, year, month);
        long total = records.size();
        if (total == 0) return List.of();

        return records.stream()
                .collect(Collectors.groupingBy(Record::getRecordType, Collectors.counting()))
                .entrySet().stream()
                .map(e -> new RecordTypeStatItem(
                        e.getKey(),
                        e.getValue(),
                        Math.round((double) e.getValue() / total * 1000.0) / 10.0
                ))
                .sorted(Comparator.comparingLong(RecordTypeStatItem::count).reversed())
                .collect(Collectors.toList());
    }

    public List<PlaceFrequencyItem> getTopPlaces(Long userId, int year, int month) {
        List<Record> records = getRecordsForMonth(userId, year, month);

        AtomicInteger rank = new AtomicInteger(1);
        return records.stream()
                .filter(r -> r.getAddress() != null && !r.getAddress().isBlank())
                .collect(Collectors.groupingBy(Record::getAddress, Collectors.counting()))
                .entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .limit(TOP_PLACES)
                .map(e -> new PlaceFrequencyItem(rank.getAndIncrement(), e.getKey(), e.getValue()))
                .collect(Collectors.toList());
    }

    private List<Record> getRecordsForMonth(Long userId, int year, int month) {
        LocalDate startDate = LocalDate.of(year, month, 1);
        LocalDate endDate = startDate.withDayOfMonth(startDate.lengthOfMonth());
        return recordRepository.findByUserIdAndRecordDateBetween(userId, startDate, endDate);
    }
}
