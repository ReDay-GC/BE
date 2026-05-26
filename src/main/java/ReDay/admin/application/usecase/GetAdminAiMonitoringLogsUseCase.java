package ReDay.admin.application.usecase;

import ReDay.admin.application.dto.response.AdminAiMonitoringLogResponse;
import ReDay.memory.domain.entity.AiProcessingLog;
import ReDay.memory.domain.repository.AiProcessingLogRepository;
import ReDay.user.domain.entity.User;
import ReDay.user.domain.repository.UserRepository;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetAdminAiMonitoringLogsUseCase {

    private final AiProcessingLogRepository aiProcessingLogRepository;
    private final UserRepository userRepository;

    public Page<AdminAiMonitoringLogResponse> execute(Pageable pageable) {
        Page<AiProcessingLog> logs = aiProcessingLogRepository.findAllByOrderByProcessedAtDesc(pageable);

        Set<Long> userIds = logs.stream()
                .map(AiProcessingLog::getUserId)
                .collect(Collectors.toSet());

        Map<Long, String> userNames = userRepository.findAllById(userIds).stream()
                .collect(Collectors.toMap(User::getId, User::getName));

        return logs.map(log -> toResponse(log, userNames));
    }

    private AdminAiMonitoringLogResponse toResponse(AiProcessingLog log, Map<Long, String> userNames) {
        String userName = userNames.getOrDefault(log.getUserId(), "알 수 없음");
        String formattedMemoryId = formatMemoryId(log.getMemoryId(), log.getProcessedAt());
        Double responseTimeSec = log.getResponseTimeMs() < 0
                ? null
                : Math.round(log.getResponseTimeMs() / 10.0) / 100.0;

        return new AdminAiMonitoringLogResponse(
                log.getId(),
                formattedMemoryId,
                log.getUserId(),
                userName,
                log.getStatus(),
                responseTimeSec,
                log.getProcessedAt()
        );
    }

    private String formatMemoryId(Long memoryId, LocalDateTime processedAt) {
        if (memoryId == null) {
            return "인사이트";
        }
        String mmdd = String.format("%02d%02d", processedAt.getMonthValue(), processedAt.getDayOfMonth());
        return String.format("MEM-%d-%s-%03d", processedAt.getYear(), mmdd, memoryId);
    }
}
