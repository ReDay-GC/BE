package ReDay.analysis.application.usecase;

import ReDay.analysis.application.dto.request.MonthlyInsightReceiveRequest;
import ReDay.analysis.domain.entity.ActivityEntry;
import ReDay.analysis.domain.entity.PersonEntry;
import ReDay.analysis.domain.service.MonthlyInsightSaveService;
import ReDay.memory.domain.entity.AiProcessingLog;
import ReDay.memory.domain.repository.AiProcessingLogRepository;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SaveMonthlyInsightUseCase {

    private final MonthlyInsightSaveService monthlyInsightSaveService;
    private final AiProcessingLogRepository aiProcessingLogRepository;

    public void execute(MonthlyInsightReceiveRequest request) {
        List<PersonEntry> topPeople = request.topPeople() == null ? List.of() :
                request.topPeople().stream()
                        .map(p -> new PersonEntry(p.name(), p.count()))
                        .toList();

        List<ActivityEntry> topActivities = request.topActivities() == null ? List.of() :
                request.topActivities().stream()
                        .map(a -> new ActivityEntry(a.activityType(), a.percentage()))
                        .toList();

        monthlyInsightSaveService.saveFromExternal(
                request.userId(),
                request.year(),
                request.month(),
                request.insightText(),
                topPeople,
                topActivities
        );

        long aiTimeMs = request.aiProcessingTimeMs() != null ? request.aiProcessingTimeMs() : -1L;
        aiProcessingLogRepository.save(AiProcessingLog.builder()
                .memoryId(null)
                .userId(request.userId())
                .status("SUCCESS")
                .responseTimeMs(aiTimeMs)
                .processedAt(LocalDateTime.now())
                .build());
    }
}
