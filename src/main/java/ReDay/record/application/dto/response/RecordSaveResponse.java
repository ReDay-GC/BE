package ReDay.record.application.dto.response;

import java.time.LocalDateTime;

public record RecordSaveResponse(
        Long recordId,
        LocalDateTime createdAt,
        String fileUrl
) {
}
