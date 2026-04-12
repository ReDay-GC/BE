package ReDay.record.application.usecase;

import ReDay.record.application.dto.response.RecordListItemResponse;
import ReDay.record.domain.service.RecordGetService;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetRecordsByDateUseCase {

    private final RecordGetService recordGetService;

    public List<RecordListItemResponse> execute(Long userId, LocalDate date) {
        return recordGetService.getByDate(userId, date)
                .stream()
                .map(RecordListItemResponse::from)
                .toList();
    }
}
