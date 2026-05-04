package ReDay.memory.application.usecase;

import ReDay.memory.application.dto.response.MemoryCalendarResponse;
import ReDay.memory.domain.service.MemoryGetService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetMemoryCalendarUseCase {

    private final MemoryGetService memoryGetService;

    public MemoryCalendarResponse execute(Long userId, int year, int month) {
        return new MemoryCalendarResponse(
                memoryGetService.getMemoryDatesByYearMonth(userId, year, month)
        );
    }
}
