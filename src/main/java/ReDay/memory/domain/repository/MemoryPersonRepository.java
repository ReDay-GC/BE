package ReDay.memory.domain.repository;

import ReDay.memory.domain.entity.Memory;
import ReDay.memory.domain.entity.MemoryPerson;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemoryPersonRepository extends JpaRepository<MemoryPerson, Long> {

    List<MemoryPerson> findAllByMemory(Memory memory);

    void deleteAllByMemory(Memory memory);
}
