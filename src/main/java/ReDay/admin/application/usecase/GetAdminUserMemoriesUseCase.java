package ReDay.admin.application.usecase;

import ReDay.admin.application.dto.response.AdminUserMemoryResponse;
import ReDay.admin.domain.service.AdminUserService;
import ReDay.memory.domain.entity.Memory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetAdminUserMemoriesUseCase {

    private final AdminUserService adminUserService;

    public List<AdminUserMemoryResponse> execute(Long userId) {
        return adminUserService.getMemoriesByUserId(userId)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    private AdminUserMemoryResponse toResponse(Memory memory) {
        long recordCount = adminUserService.getRecordCountByMemory(memory.getId());
        return new AdminUserMemoryResponse(
                memory.getId(),
                memory.getTitle(),
                memory.getSummary(),
                memory.getMemoryDate(),
                recordCount
        );
    }
}
