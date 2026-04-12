package ReDay.record.application.usecase;

import ReDay.record.domain.service.RecordGetService;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetRecordDatesByMonthUseCase {

    private final RecordGetService recordGetService;

    public List<LocalDate> execute(Long userId, int year, int month) {
        return recordGetService.getRecordDatesByMonth(userId, year, month);
    }
}
