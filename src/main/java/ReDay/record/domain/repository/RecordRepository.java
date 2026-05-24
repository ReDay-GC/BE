package ReDay.record.domain.repository;

import ReDay.record.domain.entity.Record;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RecordRepository extends JpaRepository<Record, Long> {

    @Query(value = "SELECT * FROM `record` WHERE user_id = :userId AND DATE(record_date) = :recordDate", nativeQuery = true)
    List<Record> findByUserIdAndRecordDate(@Param("userId") Long userId, @Param("recordDate") LocalDate recordDate);

    @Query(value = "SELECT DISTINCT DATE(record_date) FROM `record` WHERE user_id = :userId AND YEAR(DATE(record_date)) = :year AND MONTH(DATE(record_date)) = :month ORDER BY record_date", nativeQuery = true)
    List<LocalDate> findDistinctRecordDatesByMonth(@Param("userId") Long userId, @Param("year") int year, @Param("month") int month);

    @Query(value = "SELECT * FROM `record` WHERE user_id = :userId AND DATE(record_date) BETWEEN :startDate AND :endDate", nativeQuery = true)
    List<Record> findByUserIdAndRecordDateBetween(@Param("userId") Long userId, @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    @Query(value = "SELECT * FROM `record` WHERE user_id = :userId AND DATE(record_date) >= :startDate", nativeQuery = true)
    List<Record> findByUserIdAndRecordDateGreaterThanEqual(@Param("userId") Long userId, @Param("startDate") LocalDate startDate);

    long countByUserId(Long userId);

    long countByUserIdAndRecordType(Long userId, String recordType);

    long countByRecordType(String recordType);

    List<Record> findByUserIdAndLatitudeIsNotNullAndLongitudeIsNotNull(Long userId);

    @Query("SELECT r FROM Record r WHERE r.id IN :ids AND r.latitude IS NOT NULL AND r.longitude IS NOT NULL ORDER BY r.id ASC")
    List<Record> findWithLocationByIds(@Param("ids") List<Long> ids);

    List<Record> findAllByUserId(Long userId);

    void deleteAllByUserId(Long userId);
}
