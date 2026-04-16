package ReDay.notification.application.usecase;

import ReDay.notification.application.dto.request.NotificationSettingRequest;
import ReDay.notification.application.dto.response.NotificationSettingResponse;
import ReDay.notification.domain.entity.NotificationSetting;
import ReDay.notification.domain.service.NotificationSettingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UpdateNotificationSettingUseCase {

    private final NotificationSettingService notificationSettingService;

    public NotificationSettingResponse execute(Long userId, NotificationSettingRequest request) {
        NotificationSetting setting = notificationSettingService.updateSetting(
                userId,
                request.systemEnabled(),
                request.dailyRecordEnabled(),
                request.aiGenerationEnabled()
        );

        return new NotificationSettingResponse(
                setting.isSystemEnabled(),
                setting.isDailyRecordEnabled(),
                setting.isAiGenerationEnabled()
        );
    }
}
