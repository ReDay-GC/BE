package ReDay.notification.application.usecase;

import ReDay.notification.application.dto.response.NotificationResponse;
import ReDay.notification.domain.entity.Notification;
import ReDay.notification.domain.service.NotificationGetService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetNotificationsUseCase {

    private final NotificationGetService notificationGetService;

    public List<NotificationResponse> execute(Long userId) {
        return notificationGetService.getNotifications(userId)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    private NotificationResponse toResponse(Notification notification) {
        return new NotificationResponse(
                notification.getId(),
                notification.getType(),
                notification.getTitle(),
                notification.getContent(),
                notification.isRead(),
                notification.getCreatedAt()
        );
    }
}
