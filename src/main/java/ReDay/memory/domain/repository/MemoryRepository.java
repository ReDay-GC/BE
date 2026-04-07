package ReDay.memory.domain.repository;

import ReDay.memory.domain.entity.Memory;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemoryRepository extends JpaRepository<Memory, Long> {

    List<Memory> findAllByOrderByMemoryDateDesc();

    List<Memory> findAllByEmotionOrderByMemoryDateDesc(String emotion);

    List<Memory> findAllByMemoryDateBetweenOrderByMemoryDateDesc(
            LocalDate startDate,
            LocalDate endDate
    );

    List<Memory> findAllByTitleContainingIgnoreCaseOrderByMemoryDateDesc(String keyword);
}
