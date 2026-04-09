package ReDay.notification.domain.service;

import ReDay.notification.application.exception.NotificationNotFoundException;
import ReDay.notification.domain.entity.Notification;
import ReDay.notification.domain.repository.NotificationRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class NotificationGetService {

    private final NotificationRepository notificationRepository;

    public List<Notification> getNotifications(Long userId) {
        return notificationRepository.findAllByUserIdOrderByCreatedAtDesc(userId);
    }

    public Notification getNotification(Long notificationId) {
        return notificationRepository.findById(notificationId)
                .orElseThrow(NotificationNotFoundException::new);
    }
}
