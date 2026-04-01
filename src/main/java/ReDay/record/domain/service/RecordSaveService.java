package ReDay.record.domain.service;

import ReDay.record.application.dto.request.RecordSaveRequest;
import ReDay.record.application.dto.response.RecordSaveResponse;
import ReDay.record.application.mapper.RecordMapper;
import ReDay.record.domain.entity.Record;
import ReDay.record.domain.repository.RecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RecordSaveService {

    private final RecordRepository recordRepository;

    @Transactional
    public RecordSaveResponse save(RecordSaveRequest request) {

        Record record = RecordMapper.toEntity(request);

        Record savedRecord = recordRepository.save(record);

        return RecordMapper.toResponse(savedRecord);
    }
}
