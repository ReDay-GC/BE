package ReDay.record.application.dto.request;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record RecordSaveRequest(
        String recordType,
        String textContent,
        String mediaUrl,
        String mediaType,
        Integer voiceDurationSeconds,
        LocalDate recordDate,
        LocalDateTime recordedAt,
        Double latitude,
        Double longitude,
        String address
) {
}
