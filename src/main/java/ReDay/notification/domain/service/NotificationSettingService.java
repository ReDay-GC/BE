package ReDay.notification.domain.service;

import ReDay.notification.domain.entity.NotificationSetting;
import ReDay.notification.domain.repository.NotificationSettingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class NotificationSettingService {

    private final NotificationSettingRepository notificationSettingRepository;

    @Transactional(readOnly = true)
    public NotificationSetting getSetting(Long userId) {
        return notificationSettingRepository.findByUserId(userId)
                .orElseGet(() -> NotificationSetting.builder().userId(userId).build());
    }

    @Transactional
    public NotificationSetting updateSetting(Long userId, boolean systemEnabled,
            boolean dailyRecordEnabled, boolean aiGenerationEnabled) {
        NotificationSetting setting = notificationSettingRepository.findByUserId(userId)
                .orElseGet(() -> notificationSettingRepository.save(
                        NotificationSetting.builder().userId(userId).build()
                ));

        setting.update(systemEnabled, dailyRecordEnabled, aiGenerationEnabled);
        return setting;
    }
}
