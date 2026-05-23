package ReDay.record.application.usecase;

import ReDay.record.application.dto.request.RecordUpdateRequest;
import ReDay.record.application.dto.response.RecordListItemResponse;
import ReDay.record.domain.entity.Record;
import ReDay.record.domain.service.RecordUpdateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UpdateRecordUseCase {

    private final RecordUpdateService recordUpdateService;

    public RecordListItemResponse execute(Long recordId, RecordUpdateRequest request) {
        Record record = recordUpdateService.update(recordId, request);
        return RecordListItemResponse.from(record);
    }
}
