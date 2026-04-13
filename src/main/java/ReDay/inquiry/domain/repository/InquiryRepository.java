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

    @Query("SELECT i FROM Inquiry i WHERE " +
            "(:status IS NULL OR i.status = :status) AND " +
            "(:keyword IS NULL OR LOWER(i.title) LIKE LOWER(CONCAT('%', :keyword, '%')) OR LOWER(i.content) LIKE LOWER(CONCAT('%', :keyword, '%'))) " +
            "ORDER BY i.createdAt DESC")
    List<Inquiry> findAdminInquiries(@Param("status") InquiryStatus status, @Param("keyword") String keyword);
}
