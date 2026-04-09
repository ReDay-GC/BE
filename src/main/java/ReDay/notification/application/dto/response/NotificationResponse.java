package ReDay.notification.application.dto.response;

import ReDay.notification.domain.entity.NotificationType;
import java.time.LocalDateTime;

public record NotificationResponse(
        Long notificationId,
        NotificationType type,
        String title,
        String content,
        boolean isRead,
        LocalDateTime createdAt
) {
}
