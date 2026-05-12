package ReDay.admin.application.dto.response;

import ReDay.record.domain.entity.Record;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record AdminRecordListResponse(
        Long recordId,
        Long userId,
        String recordType,
        String textContent,
        String mediaUrl,
        String mediaType,
        LocalDate recordDate,
        LocalDateTime recordedAt,
        LocalDateTime createdAt
) {
    public static AdminRecordListResponse from(Record record) {
        return new AdminRecordListResponse(
                record.getId(),
                record.getUserId(),
                record.getRecordType(),
                record.getTextContent(),
                record.getMediaUrl(),
                record.getMediaType(),
                record.getRecordDate(),
                record.getRecordedAt(),
                record.getCreatedAt()
        );
    }
}
