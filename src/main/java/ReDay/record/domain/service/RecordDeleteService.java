package ReDay.record.domain.service;

import ReDay.record.application.exception.RecordNotFoundException;
import ReDay.record.domain.entity.Record;
import ReDay.record.domain.repository.RecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class RecordDeleteService {

    private final RecordRepository recordRepository;

    public void delete(Long recordId) {
        Record record = recordRepository.findById(recordId)
                .orElseThrow(RecordNotFoundException::new);

        recordRepository.delete(record);
    }
}
