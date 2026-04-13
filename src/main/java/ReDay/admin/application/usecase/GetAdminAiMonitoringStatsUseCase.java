package ReDay.admin.application.usecase;

import ReDay.admin.application.dto.response.AdminAiMonitoringStatsResponse;
import ReDay.memory.domain.repository.AiProcessingLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetAdminAiMonitoringStatsUseCase {

    private final AiProcessingLogRepository aiProcessingLogRepository;

    public AdminAiMonitoringStatsResponse execute() {
        long total = aiProcessingLogRepository.count();
        long success = aiProcessingLogRepository.countByStatus("SUCCESS");

        Double avgMs = aiProcessingLogRepository.findAverageResponseTimeMs();
        double avgSec = avgMs != null ? Math.round(avgMs / 10.0) / 100.0 : 0.0;

        double successRate = total > 0
                ? Math.round(success * 1000.0 / total) / 10.0
                : 0.0;

        return new AdminAiMonitoringStatsResponse(total, avgSec, successRate);
    }
}
