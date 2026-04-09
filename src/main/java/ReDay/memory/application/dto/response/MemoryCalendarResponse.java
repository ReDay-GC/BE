package ReDay.memory.application.dto.response;

import java.time.LocalDate;
import java.util.List;

public record MemoryCalendarResponse(
        List<LocalDate> datesWithMemory
) {
}
