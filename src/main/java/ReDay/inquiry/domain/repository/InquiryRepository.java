package ReDay.inquiry.domain.repository;

import ReDay.inquiry.domain.entity.Inquiry;
import ReDay.inquiry.domain.entity.InquiryStatus;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface InquiryRepository extends JpaRepository<Inquiry, Long> {

    List<Inquiry> findAllByUserIdOrderByCreatedAtDesc(Long userId);

    Optional<Inquiry> findByIdAndUserId(Long id, Long userId);

    List<Inquiry> findAllByOrderByCreatedAtDesc();

    List<Inquiry> findAllByStatusOrderByCreatedAtDesc(InquiryStatus status);

    @Query(value = "SELECT * FROM inquiry WHERE " +
            "LOWER(title) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(content) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
            "ORDER BY created_at DESC", nativeQuery = true)
    List<Inquiry> searchByKeyword(@Param("keyword") String keyword);

    @Query(value = "SELECT * FROM inquiry WHERE status = :status AND (" +
            "LOWER(title) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(content) LIKE LOWER(CONCAT('%', :keyword, '%'))) " +
            "ORDER BY created_at DESC", nativeQuery = true)
    List<Inquiry> searchByKeywordAndStatus(@Param("keyword") String keyword, @Param("status") String status);
}
