package ReDay.memory.domain.repository;

import ReDay.memory.domain.entity.Memory;
import ReDay.memory.domain.entity.MemoryTag;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MemoryTagRepository extends JpaRepository<MemoryTag, Long> {

    List<MemoryTag> findAllByMemory(Memory memory);

    void deleteAllByMemory(Memory memory);

    List<MemoryTag> findAllByTagName(String tagName);

    @Query("SELECT DISTINCT t.tagName FROM MemoryTag t ORDER BY t.tagName")
    List<String> findAllDistinctTagNames();
}
