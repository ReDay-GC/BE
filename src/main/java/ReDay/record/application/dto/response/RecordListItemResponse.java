package ReDay.record.application.dto.response;

import ReDay.record.domain.entity.Record;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record RecordListItemResponse(
        Long recordId,
        String recordType,
        String textContent,
        String fileUrl,
        Integer voiceDurationSeconds,
        LocalDate recordDate,
        LocalDateTime recordedAt,
        String address,
        Double latitude,
        Double longitude,
        LocalDateTime createdAt
) {
    public static RecordListItemResponse from(Record record) {
        return new RecordListItemResponse(
                record.getId(),
                record.getRecordType(),
                record.getTextContent(),
                record.getMediaUrl(),
                record.getVoiceDurationSeconds(),
                record.getRecordDate(),
                record.getRecordedAt(),
                record.getAddress(),
                record.getLatitude(),
                record.getLongitude(),
                record.getCreatedAt()
        );
    }
}
