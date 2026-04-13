package ReDay.record.application.usecase;

import ReDay.record.application.dto.response.RecordLocationResponse;
import ReDay.record.domain.repository.RecordRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetRecordLocationsUseCase {

    private final RecordRepository recordRepository;

    public List<RecordLocationResponse> execute(Long userId) {
        return recordRepository.findByUserIdAndLatitudeIsNotNullAndLongitudeIsNotNull(userId)
                .stream()
                .map(RecordLocationResponse::from)
                .toList();
    }
}
