package ReDay.inquiry.domain.repository;

import ReDay.inquiry.domain.entity.Inquiry;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InquiryRepository extends JpaRepository<Inquiry, Long> {

    List<Inquiry> findAllByUserIdOrderByCreatedAtDesc(Long userId);

    Optional<Inquiry> findByIdAndUserId(Long id, Long userId);
}
