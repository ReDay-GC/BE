package ReDay.record.presentation;

import ReDay.record.application.dto.request.RecordSaveRequest;
import ReDay.record.application.dto.response.RecordSaveResponse;
import ReDay.record.application.usecase.RecordSaveUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/records")
public class RecordController {

    private final RecordSaveUseCase recordSaveUseCase;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RecordSaveResponse saveRecord(@RequestBody RecordSaveRequest request) {
        return recordSaveUseCase.execute(request);
    }
}
