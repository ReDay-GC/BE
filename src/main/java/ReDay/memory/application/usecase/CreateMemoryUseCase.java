package ReDay.memory.application.usecase;

import ReDay.memory.application.dto.request.MemorySaveRequest;
import ReDay.memory.application.dto.response.MemoryListResponse;
import ReDay.memory.application.mapper.MemoryMapper;
import ReDay.memory.domain.entity.Memory;
import ReDay.memory.domain.service.MemorySaveService;
import ReDay.memory.infrastructure.AiEmbeddingClient;
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
    private final AiEmbeddingClient aiEmbeddingClient;

    public MemoryListResponse execute(Long userId, MemorySaveRequest request) {
        Memory memory = memorySaveService.save(userId, request);

        List<String> tags = request.tags() != null ? request.tags() : List.of();
        List<String> people = request.people() != null ? request.people() : List.of();

        aiEmbeddingClient.saveEmbedding(memory.getId(), memory.getTitle() + " " + memory.getSummary());

        notificationSaveService.save(
                userId,
                NotificationType.AI_GENERATION,
                "AI 생성을 잊으셨나요?",
                memory.getMemoryDate() + "에 추가한 기억이 아직 완성되지 않았어요",
                memory.getId()
        );

        return MemoryMapper.toMemoryListResponse(memory, 0, tags, people);
    }
}
