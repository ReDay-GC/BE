package ReDay.record.application.dto.request;

import java.time.LocalDate;

public record RecordUpdateRequest(
        String textContent,
        LocalDate recordDate,
        String address,
        Double latitude,
        Double longitude
) {
}
