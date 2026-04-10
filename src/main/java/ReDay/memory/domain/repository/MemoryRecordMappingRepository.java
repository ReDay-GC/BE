package ReDay.memory.domain.repository;

import ReDay.memory.domain.entity.Memory;
import ReDay.memory.domain.entity.MemoryRecordMapping;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MemoryRecordMappingRepository extends JpaRepository<MemoryRecordMapping, Long> {

    List<MemoryRecordMapping> findAllByMemory(Memory memory);

    void deleteAllByMemory(Memory memory);

    long countByMemoryId(Long memoryId);

    @Query("SELECT COUNT(DISTINCT mrm.memory.id) FROM MemoryRecordMapping mrm WHERE mrm.recordId IN (SELECT r.id FROM Record r WHERE r.userId = :userId)")
    long countMemoriesByUserId(@Param("userId") Long userId);

    @Query("SELECT DISTINCT mrm.memory FROM MemoryRecordMapping mrm WHERE mrm.recordId IN (SELECT r.id FROM Record r WHERE r.userId = :userId) ORDER BY mrm.memory.memoryDate DESC")
    List<Memory> findMemoriesByUserId(@Param("userId") Long userId);
}
