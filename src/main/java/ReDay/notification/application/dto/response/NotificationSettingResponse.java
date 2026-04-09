package ReDay.notification.application.dto.response;

public record NotificationSettingResponse(
        boolean pushEnabled,
        boolean dailyRecordEnabled,
        boolean aiGenerationEnabled
) {
}
