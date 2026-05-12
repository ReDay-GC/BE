package ReDay.record.domain.repository;

import ReDay.record.domain.entity.Record;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RecordRepository extends JpaRepository<Record, Long> {

    List<Record> findByUserIdAndRecordDate(Long userId, LocalDate recordDate);

    @Query("SELECT DISTINCT r.recordDate FROM Record r WHERE r.userId = :userId AND YEAR(r.recordDate) = :year AND MONTH(r.recordDate) = :month ORDER BY r.recordDate")
    List<LocalDate> findDistinctRecordDatesByMonth(@Param("userId") Long userId, @Param("year") int year, @Param("month") int month);

    List<Record> findByUserIdAndRecordDateBetween(Long userId, LocalDate startDate, LocalDate endDate);

    List<Record> findByUserIdAndRecordDateGreaterThanEqual(Long userId, LocalDate startDate);

    long countByUserId(Long userId);

    long countByUserIdAndRecordType(Long userId, String recordType);

    long countByRecordType(String recordType);

    List<Record> findByUserIdAndLatitudeIsNotNullAndLongitudeIsNotNull(Long userId);

    @Query("SELECT r FROM Record r WHERE r.id IN :ids AND r.latitude IS NOT NULL AND r.longitude IS NOT NULL ORDER BY r.id ASC")
    List<Record> findWithLocationByIds(@Param("ids") List<Long> ids);

    List<Record> findAllByUserId(Long userId);

    void deleteAllByUserId(Long userId);
}
