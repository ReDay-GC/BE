package ReDay.user.presentation;

import ReDay.common.response.CommonResponse;
import ReDay.common.response.ResponseMessage;
import ReDay.user.application.dto.request.UpdateProfileRequest;
import ReDay.user.application.dto.response.UserProfileResponse;
import ReDay.user.application.usecase.GetUserProfileUseCase;
import ReDay.user.application.usecase.UpdateUserProfileUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "User", description = "마이페이지 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final GetUserProfileUseCase getUserProfileUseCase;
    private final UpdateUserProfileUseCase updateUserProfileUseCase;

    @Operation(summary = "사용자 정보 조회", description = "로그인한 사용자의 프로필 정보를 조회합니다.")
    @GetMapping("/me")
    public CommonResponse<UserProfileResponse> getUserProfile(
            @AuthenticationPrincipal Long userId
    ) {
        UserProfileResponse response = getUserProfileUseCase.execute(userId);
        return CommonResponse.success(ResponseMessage.USER_PROFILE_FETCHED, response);
    }

    @Operation(summary = "프로필 수정", description = "사용자 이름을 수정합니다.")
    @PutMapping("/me")
    public CommonResponse<UserProfileResponse> updateUserProfile(
            @AuthenticationPrincipal Long userId,
            @RequestBody UpdateProfileRequest request
    ) {
        UserProfileResponse response = updateUserProfileUseCase.execute(userId, request);
        return CommonResponse.success(ResponseMessage.USER_PROFILE_UPDATED, response);
    }
}
