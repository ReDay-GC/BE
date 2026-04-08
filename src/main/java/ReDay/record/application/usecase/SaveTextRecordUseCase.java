package ReDay.record.application.usecase;

import ReDay.record.application.dto.request.TextRecordRequest;
import ReDay.record.application.dto.response.RecordSaveResponse;
import ReDay.record.domain.service.RecordSaveService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SaveTextRecordUseCase {

    private final RecordSaveService recordSaveService;

    public RecordSaveResponse execute(Long userId, TextRecordRequest request) {
        return recordSaveService.saveText(userId, request);
    }
}
