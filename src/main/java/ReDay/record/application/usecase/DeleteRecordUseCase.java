package ReDay.record.application.usecase;

import ReDay.record.domain.service.RecordDeleteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeleteRecordUseCase {

    private final RecordDeleteService recordDeleteService;

    public void execute(Long recordId) {
        recordDeleteService.delete(recordId);
    }
}
