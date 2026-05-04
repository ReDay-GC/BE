package ReDay.user.application.usecase;

import ReDay.user.domain.service.UserGetService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CheckIdDuplicateUseCase {

    private final UserGetService userGetService;

    public boolean execute(String id) {
        return !userGetService.existsById(id);
    }
}
