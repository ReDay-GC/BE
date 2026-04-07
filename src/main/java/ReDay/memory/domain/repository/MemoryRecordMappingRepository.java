package ReDay.memory.domain.repository;

import ReDay.memory.domain.entity.Memory;
import ReDay.memory.domain.entity.MemoryRecordMapping;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemoryRecordMappingRepository extends JpaRepository<MemoryRecordMapping, Long> {

    List<MemoryRecordMapping> findAllByMemory(Memory memory);

    void deleteAllByMemory(Memory memory);
}
