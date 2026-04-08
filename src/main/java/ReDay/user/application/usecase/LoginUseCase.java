package ReDay.user.application.usecase;

import ReDay.user.application.dto.request.LoginRequest;
import ReDay.user.application.dto.response.LoginResponse;
import ReDay.user.domain.service.UserLoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LoginUseCase {

    private final UserLoginService userLoginService;

    public LoginResponse execute(LoginRequest request) {
        return userLoginService.login(request);
    }
}
