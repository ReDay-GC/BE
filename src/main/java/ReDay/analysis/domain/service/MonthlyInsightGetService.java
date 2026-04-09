package ReDay.analysis.domain.service;

import ReDay.analysis.domain.entity.MonthlyInsight;
import ReDay.analysis.domain.repository.MonthlyInsightRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MonthlyInsightGetService {

    private final MonthlyInsightRepository monthlyInsightRepository;

    public Optional<MonthlyInsight> findInsight(Long userId, int year, int month) {
        return monthlyInsightRepository.findByUserIdAndYearAndMonth(userId, year, month);
    }
}
