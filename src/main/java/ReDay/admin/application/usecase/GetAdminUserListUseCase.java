package ReDay.admin.application.usecase;

import ReDay.admin.application.dto.response.AdminUserListResponse;
import ReDay.admin.domain.service.AdminUserService;
import ReDay.user.domain.entity.User;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetAdminUserListUseCase {

    private final AdminUserService adminUserService;

    public List<AdminUserListResponse> execute(String search) {
        return adminUserService.getUserList(search)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    private AdminUserListResponse toResponse(User user) {
        long memoryCount = adminUserService.getMemoryCountByUserId(user.getId());
        return new AdminUserListResponse(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getCreatedAt(),
                memoryCount
        );
    }
}
