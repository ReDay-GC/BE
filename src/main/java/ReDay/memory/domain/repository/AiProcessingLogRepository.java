package ReDay.memory.domain.repository;

import ReDay.memory.domain.entity.AiProcessingLog;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AiProcessingLogRepository extends JpaRepository<AiProcessingLog, Long> {

    List<AiProcessingLog> findAllByOrderByProcessedAtDesc();

    Page<AiProcessingLog> findAllByOrderByProcessedAtDesc(Pageable pageable);

    long countByStatus(String status);

    @Query("SELECT AVG(a.responseTimeMs) FROM AiProcessingLog a")
    Double findAverageResponseTimeMs();
}
