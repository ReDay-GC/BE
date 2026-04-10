package ReDay.admin.application.dto.response;

public record AdminServiceStatsResponse(
        long todayMemoryCount,
        long totalMemoryCount,
        long totalRecordCount,
        long photoCount,
        long textCount,
        long voiceCount,
        double photoPercent,
        double textPercent,
        double voicePercent
) {
}
