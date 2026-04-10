package ReDay.user.application.usecase;

import ReDay.user.application.dto.request.UpdateProfileRequest;
import ReDay.user.application.dto.response.UserProfileResponse;
import ReDay.user.domain.entity.User;
import ReDay.user.domain.service.UserUpdateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UpdateUserProfileUseCase {

    private final UserUpdateService userUpdateService;

    public UserProfileResponse execute(Long userId, UpdateProfileRequest request) {
        User user = userUpdateService.updateName(userId, request.name());
        return new UserProfileResponse(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getCreatedAt()
        );
    }
}
