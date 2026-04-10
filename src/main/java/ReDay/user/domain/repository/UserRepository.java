package ReDay.user.domain.repository;

import ReDay.user.domain.entity.User;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByEmail(String email);

    Optional<User> findByEmail(String email);

    List<User> findAllByNameContainingIgnoreCaseOrderByCreatedAtDesc(String name);

    long countByCreatedAtAfter(LocalDateTime dateTime);
}
