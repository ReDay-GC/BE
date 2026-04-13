package ReDay.record.application.dto.response;

public record RecordSummaryResponse(
        long photoCount,
        long textCount,
        long voiceCount
) {
}
