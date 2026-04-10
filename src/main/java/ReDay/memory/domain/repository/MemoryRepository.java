package ReDay.memory.domain.repository;

import ReDay.memory.domain.entity.Memory;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemoryRepository extends JpaRepository<Memory, Long> {

    List<Memory> findAllByOrderByMemoryDateDesc();

    List<Memory> findAllByTitleContainingIgnoreCaseOrderByMemoryDateDesc(String keyword);

    List<Memory> findAllByMemoryDate(LocalDate date);

    List<Memory> findAllByMemoryDateBetween(LocalDate startDate, LocalDate endDate);

    List<Memory> findAllByLocationIsNotNull();

    List<Memory> findAllByLocation(String location);

    long countByCreatedAtAfter(LocalDateTime dateTime);
}
