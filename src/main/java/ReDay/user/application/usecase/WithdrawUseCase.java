package ReDay.user.application.usecase;

import ReDay.user.domain.service.UserDeleteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WithdrawUseCase {

    private final UserDeleteService userDeleteService;

    public void execute(Long userId, String token) {
        userDeleteService.delete(userId, token);
    }
}
