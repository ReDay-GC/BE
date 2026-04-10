package ReDay.record.domain.repository;

import ReDay.record.domain.entity.Record;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecordRepository extends JpaRepository<Record, Long> {

    List<Record> findByUserIdAndRecordDateBetween(Long userId, LocalDate startDate, LocalDate endDate);

    List<Record> findByUserIdAndRecordDateGreaterThanEqual(Long userId, LocalDate startDate);

    long countByUserId(Long userId);

    long countByUserIdAndRecordType(Long userId, String recordType);

    long countByRecordType(String recordType);
}
