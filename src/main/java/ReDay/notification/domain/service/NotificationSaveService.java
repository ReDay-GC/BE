package ReDay.notification.domain.service;

import ReDay.notification.domain.entity.Notification;
import ReDay.notification.domain.entity.NotificationSetting;
import ReDay.notification.domain.entity.NotificationType;
import ReDay.notification.domain.repository.NotificationRepository;
import ReDay.notification.domain.repository.NotificationSettingRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class NotificationSaveService {

    private final NotificationRepository notificationRepository;
    private final NotificationSettingRepository notificationSettingRepository;

    @Transactional
    public void save(Long userId, NotificationType type, String title, String content, Long relatedId) {
        if (!isAllowed(userId, type)) {
            return;
        }
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
                .filter(userId -> isAllowed(userId, type))
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

    private boolean isAllowed(Long userId, NotificationType type) {
        NotificationSetting setting = notificationSettingRepository.findByUserId(userId)
                .orElse(null);

        if (setting == null) {
            return true;
        }
        return switch (type) {
            case NOTICE, INQUIRY_ANSWER, MAINTENANCE -> setting.isSystemEnabled();
            case DAILY_RECORD -> setting.isDailyRecordEnabled();
            case AI_GENERATION -> setting.isAiGenerationEnabled();
        };
    }
}
