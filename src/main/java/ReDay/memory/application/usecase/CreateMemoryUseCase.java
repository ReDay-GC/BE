package ReDay.memory.application.usecase;

import ReDay.memory.application.dto.request.MemorySaveRequest;
import ReDay.memory.application.dto.response.MemoryListResponse;
import ReDay.memory.application.mapper.MemoryMapper;
import ReDay.memory.domain.entity.Memory;
import ReDay.memory.domain.service.MemorySaveService;
import ReDay.notification.domain.entity.NotificationType;
import ReDay.notification.domain.service.NotificationSaveService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateMemoryUseCase {

    private final MemorySaveService memorySaveService;
    private final NotificationSaveService notificationSaveService;

    public MemoryListResponse execute(Long userId, MemorySaveRequest request) {
        Memory memory = memorySaveService.save(request);

        List<String> tags = request.tags() != null ? request.tags() : List.of();
        List<String> people = request.people() != null ? request.people() : List.of();

        notificationSaveService.save(
                userId,
                NotificationType.AI_GENERATION,
                "기억이 생성됐어요",
                "'" + memory.getTitle() + "' 기억이 생성되었습니다.",
                memory.getId()
        );

        return MemoryMapper.toMemoryListResponse(memory, 0, tags, people);
    }
}
