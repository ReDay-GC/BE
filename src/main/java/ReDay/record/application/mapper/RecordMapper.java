package ReDay.record.application.mapper;

import ReDay.record.application.dto.request.RecordSaveRequest;
import ReDay.record.application.dto.response.RecordSaveResponse;
import ReDay.record.domain.entity.Record;
import java.time.LocalDateTime;

public class RecordMapper {

    public static Record toEntity(RecordSaveRequest request) {
        return Record.builder()
                .recordType(request.recordType())
                .textContent(request.textContent())
                .mediaUrl(request.mediaUrl())
                .mediaType(request.mediaType())
                .voiceDurationSeconds(request.voiceDurationSeconds())
                .recordDate(request.recordDate())
                .recordedAt(request.recordedAt())
                .latitude(request.latitude())
                .longitude(request.longitude())
                .address(request.address())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static RecordSaveResponse toResponse(Record record) {
        return new RecordSaveResponse(
                record.getId(),
                record.getCreatedAt()
        );
    }
}
