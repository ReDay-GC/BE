package ReDay.notification.application.usecase;

import ReDay.notification.domain.service.NotificationReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReadNotificationUseCase {

    private final NotificationReadService notificationReadService;

    public void execute(Long notificationId) {
        notificationReadService.markAsRead(notificationId);
    }
}
