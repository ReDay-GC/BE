package ReDay.admin.application.usecase;

import ReDay.admin.application.dto.response.AdminUserDetailResponse;
import ReDay.admin.application.dto.response.AdminUserMemoryResponse;
import ReDay.admin.domain.service.AdminUserService;
import ReDay.memory.domain.entity.Memory;
import ReDay.user.domain.entity.User;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetAdminUserDetailUseCase {

    private final AdminUserService adminUserService;

    public AdminUserDetailResponse execute(Long userId) {
        User user = adminUserService.getUser(userId);

        long totalMemories = adminUserService.getMemoryCountByUserId(userId);
        long totalRecords = adminUserService.getTotalRecordCount(userId);
        long photoCount = adminUserService.getRecordCountByType(userId, "PHOTO");
        long textCount = adminUserService.getRecordCountByType(userId, "TEXT");
        long voiceCount = adminUserService.getRecordCountByType(userId, "VOICE");

        List<AdminUserMemoryResponse> memories = adminUserService.getMemoriesByUserId(userId)
                .stream()
                .map(this::toMemoryResponse)
                .toList();

        return new AdminUserDetailResponse(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getCreatedAt(),
                totalMemories,
                totalRecords,
                photoCount,
                textCount,
                voiceCount,
                memories
        );
    }

    private AdminUserMemoryResponse toMemoryResponse(Memory memory) {
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
