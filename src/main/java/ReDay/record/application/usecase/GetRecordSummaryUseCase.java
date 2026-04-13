package ReDay.record.application.usecase;

import ReDay.record.application.dto.response.RecordSummaryResponse;
import ReDay.record.domain.repository.RecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetRecordSummaryUseCase {

    private final RecordRepository recordRepository;

    public RecordSummaryResponse execute(Long userId) {
        return new RecordSummaryResponse(
                recordRepository.countByUserIdAndRecordType(userId, "PHOTO"),
                recordRepository.countByUserIdAndRecordType(userId, "TEXT"),
                recordRepository.countByUserIdAndRecordType(userId, "VOICE")
        );
    }
}
