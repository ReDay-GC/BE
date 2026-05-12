package ReDay.admin.application.usecase;

import ReDay.admin.application.dto.response.AdminMemoryListResponse;
import ReDay.memory.domain.repository.MemoryRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetAdminAllMemoriesUseCase {

    private final MemoryRepository memoryRepository;

    public List<AdminMemoryListResponse> execute() {
        return memoryRepository.findAllByOrderByCreatedAtDesc()
                .stream()
                .map(AdminMemoryListResponse::from)
                .toList();
    }
}
