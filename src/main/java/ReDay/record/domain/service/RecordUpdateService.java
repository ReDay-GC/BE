package ReDay.record.domain.service;

import ReDay.record.application.dto.request.RecordUpdateRequest;
import ReDay.record.application.exception.RecordNotFoundException;
import ReDay.record.domain.entity.Record;
import ReDay.record.domain.repository.RecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class RecordUpdateService {

    private final RecordRepository recordRepository;

    public Record update(Long recordId, RecordUpdateRequest request) {
        Record record = recordRepository.findById(recordId)
                .orElseThrow(RecordNotFoundException::new);

        String textContent = request.textContent() != null ? request.textContent() : record.getTextContent();
        var recordDate = request.recordDate() != null ? request.recordDate() : record.getRecordDate();
        String address = request.address() != null ? request.address() : record.getAddress();
        Double latitude = request.latitude() != null ? request.latitude() : record.getLatitude();
        Double longitude = request.longitude() != null ? request.longitude() : record.getLongitude();

        record.update(textContent, recordDate, address, latitude, longitude);

        return record;
    }
}
