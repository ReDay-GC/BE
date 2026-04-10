package ReDay.admin.application.usecase;

import ReDay.admin.application.dto.response.AdminUserStatsResponse;
import ReDay.admin.domain.service.AdminUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetAdminUserStatsUseCase {

    private final AdminUserService adminUserService;

    public AdminUserStatsResponse execute() {
        long total = adminUserService.getTotalUserCount();
        long newToday = adminUserService.getNewUserCountToday();
        return new AdminUserStatsResponse(total, newToday);
    }
}
