package ReDay.user.application.usecase;

import ReDay.user.application.dto.request.SignUpRequest;
import ReDay.user.application.dto.response.SignUpResponse;
import ReDay.user.domain.service.UserSaveService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SignUpUseCase {

    private final UserSaveService userSaveService;

    public SignUpResponse execute(SignUpRequest request) {
        return userSaveService.save(request);
    }
}
