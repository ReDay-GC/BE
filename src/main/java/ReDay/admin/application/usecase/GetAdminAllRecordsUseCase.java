package ReDay.admin.application.usecase;

import ReDay.admin.application.dto.response.AdminRecordListResponse;
import ReDay.record.domain.repository.RecordRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetAdminAllRecordsUseCase {

    private final RecordRepository recordRepository;

    public List<AdminRecordListResponse> execute() {
        return recordRepository.findAll(Sort.by(Sort.Direction.DESC, "createdAt"))
                .stream()
                .map(AdminRecordListResponse::from)
                .toList();
    }
}
