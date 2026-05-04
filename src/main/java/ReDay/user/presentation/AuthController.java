package ReDay.user.presentation;

import ReDay.common.response.CommonResponse;
import ReDay.common.response.ResponseMessage;
import ReDay.user.application.dto.request.LoginRequest;
import ReDay.user.application.dto.request.SignUpRequest;
import ReDay.user.application.dto.response.LoginResponse;
import ReDay.user.application.dto.response.SignUpResponse;
import ReDay.user.application.usecase.CheckIdDuplicateUseCase;
import ReDay.user.application.usecase.LoginUseCase;
import ReDay.user.application.usecase.LogoutUseCase;
import ReDay.user.application.usecase.SignUpUseCase;
import ReDay.user.application.usecase.WithdrawUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Auth", description = "인증 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final SignUpUseCase signUpUseCase;
    private final LoginUseCase loginUseCase;
    private final LogoutUseCase logoutUseCase;
    private final WithdrawUseCase withdrawUseCase;
    private final CheckIdDuplicateUseCase checkIdDuplicateUseCase;

    @Operation(summary = "회원가입")
    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public CommonResponse<SignUpResponse> signUp(@Valid @RequestBody SignUpRequest request) {
        SignUpResponse response = signUpUseCase.execute(request);
        return CommonResponse.success(ResponseMessage.SIGNUP_SUCCESS, response);
    }

    @Operation(summary = "로그인")
    @PostMapping("/login")
    public CommonResponse<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
        LoginResponse response = loginUseCase.execute(request);
        return CommonResponse.success(ResponseMessage.LOGIN_SUCCESS, response);
    }

    @Operation(summary = "아이디 중복 확인", description = "true = 사용 가능, false = 이미 사용 중")
    @GetMapping("/check-id")
    public CommonResponse<Boolean> checkId(@RequestParam String id) {
        boolean available = checkIdDuplicateUseCase.execute(id);
        ResponseMessage message = available ? ResponseMessage.ID_AVAILABLE : ResponseMessage.ID_ALREADY_EXISTS;
        return CommonResponse.success(message, available);
    }

    @Operation(summary = "로그아웃")
    @PostMapping("/logout")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void logout(@RequestHeader("Authorization") String bearerToken) {
        String token = bearerToken.substring("Bearer ".length());
        logoutUseCase.execute(token);
    }

    @Operation(summary = "회원탈퇴")
    @DeleteMapping("/withdraw")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void withdraw(@AuthenticationPrincipal Long userId,
                         @RequestHeader("Authorization") String bearerToken) {
        String token = bearerToken.substring("Bearer ".length());
        withdrawUseCase.execute(userId, token);
    }
}
