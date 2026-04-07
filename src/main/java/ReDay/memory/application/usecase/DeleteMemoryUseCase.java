package ReDay.memory.application.usecase;

import ReDay.memory.domain.service.MemoryDeleteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeleteMemoryUseCase {

    private final MemoryDeleteService memoryDeleteService;

    public void execute(Long memoryId) {
        memoryDeleteService.delete(memoryId);
    }
}
