package ReDay.notification.domain.service;

import ReDay.notification.application.exception.NotificationNotFoundException;
import ReDay.notification.domain.entity.Notification;
import ReDay.notification.domain.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class NotificationReadService {

    private final NotificationRepository notificationRepository;

    public void markAsRead(Long notificationId) {
        Notification notification = notificationRepository.findById(notificationId)
                .orElseThrow(NotificationNotFoundException::new);

        notification.markAsRead();
    }
}
