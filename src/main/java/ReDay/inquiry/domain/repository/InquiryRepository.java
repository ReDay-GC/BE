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

    @Query("SELECT i FROM Inquiry i WHERE " +
            "LOWER(i.title) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(i.content) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
            "ORDER BY i.createdAt DESC")
    List<Inquiry> searchByKeyword(@Param("keyword") String keyword);

    @Query("SELECT i FROM Inquiry i WHERE i.status = :status AND (" +
            "LOWER(i.title) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(i.content) LIKE LOWER(CONCAT('%', :keyword, '%'))) " +
            "ORDER BY i.createdAt DESC")
    List<Inquiry> searchByKeywordAndStatus(@Param("keyword") String keyword, @Param("status") InquiryStatus status);
}
