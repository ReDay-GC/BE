package ReDay.user.application.usecase;

import ReDay.user.application.dto.response.UserProfileResponse;
import ReDay.user.domain.entity.User;
import ReDay.user.domain.service.UserGetService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetUserProfileUseCase {

    private final UserGetService userGetService;

    public UserProfileResponse execute(Long userId) {
        User user = userGetService.getUser(userId);
        return new UserProfileResponse(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getCreatedAt()
        );
    }
}
