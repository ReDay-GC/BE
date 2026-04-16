package ReDay.notification.application.dto.response;

public record NotificationSettingResponse(
        boolean systemEnabled,
        boolean dailyRecordEnabled,
        boolean aiGenerationEnabled
) {
}
