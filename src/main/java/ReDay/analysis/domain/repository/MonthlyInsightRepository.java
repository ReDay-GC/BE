package ReDay.analysis.domain.repository;

import ReDay.analysis.domain.entity.MonthlyInsight;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MonthlyInsightRepository extends JpaRepository<MonthlyInsight, Long> {

    Optional<MonthlyInsight> findByUserIdAndYearAndMonth(Long userId, int year, int month);

    boolean existsByUserIdAndYearAndMonth(Long userId, int year, int month);
}
