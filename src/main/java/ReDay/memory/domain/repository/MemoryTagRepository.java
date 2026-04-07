package ReDay.memory.domain.repository;

import ReDay.memory.domain.entity.Memory;
import ReDay.memory.domain.entity.MemoryTag;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemoryTagRepository extends JpaRepository<MemoryTag, Long> {

    List<MemoryTag> findAllByMemory(Memory memory);

    void deleteAllByMemory(Memory memory);
}
