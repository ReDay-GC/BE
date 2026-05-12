package ReDay.memory.domain.repository;

import ReDay.memory.domain.entity.Memory;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MemoryRepository extends JpaRepository<Memory, Long> {

    List<Memory> findAllByUserIdOrderByMemoryDateDesc(Long userId);

    @Query("SELECT m FROM Memory m WHERE m.userId = :userId AND (" +
            "LOWER(m.title) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(m.summary) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(m.location) LIKE LOWER(CONCAT('%', :keyword, '%'))) " +
            "ORDER BY m.memoryDate DESC")
    List<Memory> searchByUserIdAndKeyword(@Param("userId") Long userId, @Param("keyword") String keyword);

    List<Memory> findAllByUserIdAndMemoryDate(Long userId, LocalDate date);

    List<Memory> findAllByUserIdAndMemoryDateBetween(Long userId, LocalDate startDate, LocalDate endDate);

    List<Memory> findAllByUserIdAndLocationIsNotNull(Long userId);

    List<Memory> findAllByUserIdAndLocation(Long userId, String location);

    long countByCreatedAtAfter(LocalDateTime dateTime);

    List<Memory> findAllByCreatedAtAfterOrderByCreatedAtDesc(LocalDateTime dateTime);

    List<Memory> findAllByOrderByCreatedAtDesc();
}
