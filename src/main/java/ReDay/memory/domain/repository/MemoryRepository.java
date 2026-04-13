package ReDay.memory.domain.repository;

import ReDay.memory.domain.entity.Memory;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MemoryRepository extends JpaRepository<Memory, Long> {

    List<Memory> findAllByOrderByMemoryDateDesc();

    @Query("SELECT m FROM Memory m WHERE " +
            "LOWER(m.title) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(m.summary) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(m.location) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
            "ORDER BY m.memoryDate DESC")
    List<Memory> searchByKeyword(@Param("keyword") String keyword);

    List<Memory> findAllByMemoryDate(LocalDate date);

    List<Memory> findAllByMemoryDateBetween(LocalDate startDate, LocalDate endDate);

    List<Memory> findAllByLocationIsNotNull();

    List<Memory> findAllByLocation(String location);

    long countByCreatedAtAfter(LocalDateTime dateTime);
}
