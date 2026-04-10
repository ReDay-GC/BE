package ReDay.admin.application.usecase;

import ReDay.admin.application.dto.response.AdminServiceStatsResponse;
import ReDay.memory.domain.repository.MemoryRepository;
import ReDay.record.domain.repository.RecordRepository;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetAdminServiceStatsUseCase {

    private final MemoryRepository memoryRepository;
    private final RecordRepository recordRepository;

    public AdminServiceStatsResponse execute() {
        long todayMemoryCount = memoryRepository.countByCreatedAtAfter(LocalDate.now().atStartOfDay());
        long totalMemoryCount = memoryRepository.count();

        long photoCount = recordRepository.countByRecordType("PHOTO");
        long textCount = recordRepository.countByRecordType("TEXT");
        long voiceCount = recordRepository.countByRecordType("VOICE");
        long totalRecordCount = recordRepository.count();

        double photoPercent = totalRecordCount > 0 ? Math.round(photoCount * 1000.0 / totalRecordCount) / 10.0 : 0;
        double textPercent = totalRecordCount > 0 ? Math.round(textCount * 1000.0 / totalRecordCount) / 10.0 : 0;
        double voicePercent = totalRecordCount > 0 ? Math.round(voiceCount * 1000.0 / totalRecordCount) / 10.0 : 0;

        return new AdminServiceStatsResponse(
                todayMemoryCount,
                totalMemoryCount,
                totalRecordCount,
                photoCount,
                textCount,
                voiceCount,
                photoPercent,
                textPercent,
                voicePercent
        );
    }
}
