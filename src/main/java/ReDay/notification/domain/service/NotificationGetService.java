package ReDay.notification.domain.service;

import ReDay.notification.application.exception.NotificationNotFoundException;
import ReDay.notification.domain.entity.Notification;
import ReDay.notification.domain.entity.NotificationCategory;
import ReDay.notification.domain.entity.NotificationType;
import ReDay.notification.domain.repository.NotificationRepository;
import java.util.Arrays;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class NotificationGetService {

    private final NotificationRepository notificationRepository;

    public List<Notification> getNotifications(Long userId, NotificationCategory category) {
        if (category == null || category == NotificationCategory.ALL) {
            return notificationRepository.findAllByUserIdOrderByCreatedAtDesc(userId);
        }

        List<NotificationType> types = Arrays.stream(NotificationType.values())
                .filter(type -> type.getCategory() == category)
                .toList();

        return notificationRepository.findAllByUserIdAndTypeInOrderByCreatedAtDesc(userId, types);
    }

    public Notification getNotification(Long notificationId) {
        return notificationRepository.findById(notificationId)
                .orElseThrow(NotificationNotFoundException::new);
    }
}
