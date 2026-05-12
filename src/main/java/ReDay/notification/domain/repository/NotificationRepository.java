package ReDay.notification.domain.repository;

import ReDay.notification.domain.entity.Notification;
import ReDay.notification.domain.entity.NotificationType;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

    List<Notification> findAllByUserIdOrderByCreatedAtDesc(Long userId);

    List<Notification> findAllByUserIdAndTypeInOrderByCreatedAtDesc(Long userId, List<NotificationType> types);

    void deleteAllByUserId(Long userId);
}
