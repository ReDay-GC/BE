package ReDay.memory.application.dto.request;

import java.time.LocalDate;

public record MemorySearchRequest(
        String keyword,
        String emotion,
        LocalDate startDate,
        LocalDate endDate
) {
}
