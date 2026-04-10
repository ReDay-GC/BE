package ReDay.admin.application.usecase;

import ReDay.admin.application.dto.request.AdminUpdateUserRequest;
import ReDay.admin.application.dto.response.AdminUserListResponse;
import ReDay.admin.domain.service.AdminUserService;
import ReDay.user.domain.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UpdateAdminUserUseCase {

    private final AdminUserService adminUserService;

    public AdminUserListResponse execute(Long userId, AdminUpdateUserRequest request) {
        User user = adminUserService.updateUser(userId, request.name());
        long memoryCount = adminUserService.getMemoryCountByUserId(userId);
        return new AdminUserListResponse(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getCreatedAt(),
                memoryCount
        );
    }
}
