package ReDay.admin.application.dto.response;

import java.time.LocalDate;

public record AdminUserMemoryResponse(
        Long memoryId,
        String title,
        String summary,
        LocalDate memoryDate,
        long recordCount
) {
}
