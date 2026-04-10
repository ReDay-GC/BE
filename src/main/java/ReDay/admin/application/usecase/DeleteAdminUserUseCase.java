package ReDay.admin.application.usecase;

import ReDay.admin.domain.service.AdminUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeleteAdminUserUseCase {

    private final AdminUserService adminUserService;

    public void execute(Long userId) {
        adminUserService.deleteUser(userId);
    }
}
