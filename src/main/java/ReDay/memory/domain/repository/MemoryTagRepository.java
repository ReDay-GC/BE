package ReDay.memory.domain.repository;

import ReDay.memory.domain.entity.Memory;
import ReDay.memory.domain.entity.MemoryTag;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MemoryTagRepository extends JpaRepository<MemoryTag, Long> {

    List<MemoryTag> findAllByMemory(Memory memory);

    void deleteAllByMemory(Memory memory);

    @Query("SELECT t FROM MemoryTag t WHERE t.tagName = :tagName AND t.memory.userId = :userId")
    List<MemoryTag> findAllByTagNameAndUserId(@Param("tagName") String tagName, @Param("userId") Long userId);

    @Query("SELECT DISTINCT t.tagName FROM MemoryTag t WHERE t.memory.userId = :userId ORDER BY t.tagName")
    List<String> findAllDistinctTagNamesByUserId(@Param("userId") Long userId);
}
