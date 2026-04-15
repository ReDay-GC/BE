package ReDay.notification.domain.service;

import ReDay.notification.domain.entity.Notification;
import ReDay.notification.domain.entity.NotificationType;
import ReDay.notification.domain.repository.NotificationRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class NotificationSaveService {

    private final NotificationRepository notificationRepository;

    @Transactional
    public void save(Long userId, NotificationType type, String title, String content, Long relatedId) {
        notificationRepository.save(Notification.builder()
                .userId(userId)
                .type(type)
                .title(title)
                .content(content)
                .relatedId(relatedId)
                .build());
    }

    @Transactional
    public void saveAll(List<Long> userIds, NotificationType type, String title, String content, Long relatedId) {
        List<Notification> notifications = userIds.stream()
                .map(userId -> Notification.builder()
                        .userId(userId)
                        .type(type)
                        .title(title)
                        .content(content)
                        .relatedId(relatedId)
                        .build())
                .toList();
        notificationRepository.saveAll(notifications);
    }
}
