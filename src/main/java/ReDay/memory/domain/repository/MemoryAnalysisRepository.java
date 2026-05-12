package ReDay.memory.domain.repository;

import ReDay.memory.domain.entity.MemoryAnalysis;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemoryAnalysisRepository extends JpaRepository<MemoryAnalysis, Long> {

    Optional<MemoryAnalysis> findByMemoryId(Long memoryId);

    void deleteByMemoryId(Long memoryId);
}
