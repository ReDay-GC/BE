package ReDay.admin.application.usecase;

import ReDay.admin.application.dto.request.AdminLoginRequest;
import ReDay.admin.application.dto.response.AdminLoginResponse;
import ReDay.admin.domain.entity.Admin;
import ReDay.admin.domain.service.AdminAuthService;
import ReDay.config.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AdminLoginUseCase {

    private final AdminAuthService adminAuthService;
    private final JwtProvider jwtProvider;

    public AdminLoginResponse execute(AdminLoginRequest request) {
        Admin admin = adminAuthService.authenticate(request.email(), request.password());
        String token = jwtProvider.generateAdminToken(admin.getId());

        return new AdminLoginResponse(
                admin.getId(),
                admin.getName(),
                admin.getEmail(),
                token
        );
    }
}
