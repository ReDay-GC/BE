package ReDay.admin.application.usecase;

import ReDay.admin.application.dto.response.AdminAiLogResponse;
import ReDay.admin.application.dto.response.AdminAiStatsResponse;
import ReDay.memory.domain.entity.AiProcessingLog;
import ReDay.memory.domain.repository.AiProcessingLogRepository;
import ReDay.user.domain.repository.UserRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetAdminAiStatsUseCase {

    private final AiProcessingLogRepository aiProcessingLogRepository;
    private final UserRepository userRepository;

    public AdminAiStatsResponse execute() {
        long totalRequests = aiProcessingLogRepository.count();
        long successCount = aiProcessingLogRepository.countByStatus("SUCCESS");

        Double avgMs = aiProcessingLogRepository.findAverageResponseTimeMs();
        double averageResponseTimeSec = avgMs != null ? Math.round(avgMs / 10.0) / 100.0 : 0.0;

        double successRate = totalRequests > 0
                ? Math.round(successCount * 1000.0 / totalRequests) / 10.0
                : 0.0;

        List<AdminAiLogResponse> logs = aiProcessingLogRepository.findAllByOrderByProcessedAtDesc()
                .stream()
                .map(this::toResponse)
                .toList();

        return new AdminAiStatsResponse(totalRequests, averageResponseTimeSec, successRate, logs);
    }

    private AdminAiLogResponse toResponse(AiProcessingLog log) {
        String userName = userRepository.findById(log.getUserId())
                .map(u -> u.getName())
                .orElse("알 수 없음");

        double responseTimeSec = Math.round(log.getResponseTimeMs() / 10.0) / 100.0;

        return new AdminAiLogResponse(
                log.getId(),
                log.getMemoryId(),
                log.getUserId(),
                userName,
                log.getStatus(),
                responseTimeSec,
                log.getProcessedAt()
        );
    }
}
