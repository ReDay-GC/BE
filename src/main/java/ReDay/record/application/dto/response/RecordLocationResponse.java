package ReDay.record.application.dto.response;

import ReDay.record.domain.entity.Record;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record RecordLocationResponse(
        Long recordId,
        String recordType,
        String fileUrl,
        String address,
        Double latitude,
        Double longitude,
        LocalDate recordDate,
        LocalDateTime recordedAt
) {
    public static RecordLocationResponse from(Record record) {
        return new RecordLocationResponse(
                record.getId(),
                record.getRecordType(),
                record.getMediaUrl(),
                record.getAddress(),
                record.getLatitude(),
                record.getLongitude(),
                record.getRecordDate(),
                record.getRecordedAt()
        );
    }
}
