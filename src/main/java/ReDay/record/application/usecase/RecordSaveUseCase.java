package ReDay.record.application.usecase;

import ReDay.record.application.dto.request.RecordSaveRequest;
import ReDay.record.application.dto.response.RecordSaveResponse;
import ReDay.record.domain.service.RecordSaveService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RecordSaveUseCase {

    private final RecordSaveService recordSaveService;

    public RecordSaveResponse execute(RecordSaveRequest request) {
        return recordSaveService.save(request);
    }
}
