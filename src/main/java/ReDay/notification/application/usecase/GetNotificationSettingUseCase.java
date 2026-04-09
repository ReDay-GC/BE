package ReDay.notification.application.usecase;

import ReDay.notification.application.dto.response.NotificationSettingResponse;
import ReDay.notification.domain.entity.NotificationSetting;
import ReDay.notification.domain.service.NotificationSettingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetNotificationSettingUseCase {

    private final NotificationSettingService notificationSettingService;

    public NotificationSettingResponse execute(Long userId) {
        NotificationSetting setting = notificationSettingService.getSetting(userId);

        return new NotificationSettingResponse(
                setting.isPushEnabled(),
                setting.isDailyRecordEnabled(),
                setting.isAiGenerationEnabled()
        );
    }
}
