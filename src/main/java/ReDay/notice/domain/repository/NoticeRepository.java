package ReDay.notice.domain.repository;

import ReDay.notice.domain.entity.Notice;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeRepository extends JpaRepository<Notice, Long> {

    List<Notice> findAllByOrderByCreatedAtDesc();

    List<Notice> findAllByIsPublicTrueOrderByCreatedAtDesc();
}
