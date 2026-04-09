package ReDay.notification.application.dto.request;

public record NotificationSettingRequest(
        boolean pushEnabled,
        boolean dailyRecordEnabled,
        boolean aiGenerationEnabled
) {
}
