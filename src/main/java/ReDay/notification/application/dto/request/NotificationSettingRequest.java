package ReDay.notification.application.dto.request;

public record NotificationSettingRequest(
        boolean systemEnabled,
        boolean dailyRecordEnabled,
        boolean aiGenerationEnabled
) {
}
