package ReDay.user.application.usecase;

import ReDay.user.domain.service.UserLogoutService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LogoutUseCase {

    private final UserLogoutService userLogoutService;

    public void execute(String token) {
        userLogoutService.logout(token);
    }
}
