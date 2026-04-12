package ReDay.record.domain.service;

import ReDay.record.domain.entity.Record;
import ReDay.record.domain.repository.RecordRepository;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class RecordGetService {

    private final RecordRepository recordRepository;

    @Transactional(readOnly = true)
    public List<Record> getByDate(Long userId, LocalDate date) {
        return recordRepository.findByUserIdAndRecordDate(userId, date);
    }

    @Transactional(readOnly = true)
    public List<LocalDate> getRecordDatesByMonth(Long userId, int year, int month) {
        return recordRepository.findDistinctRecordDatesByMonth(userId, year, month);
    }
}
