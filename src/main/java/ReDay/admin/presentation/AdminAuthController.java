package ReDay.admin.presentation;

import ReDay.admin.application.dto.request.AdminLoginRequest;
import ReDay.admin.application.dto.response.AdminLoginResponse;
import ReDay.admin.application.usecase.AdminLoginUseCase;
import ReDay.common.response.CommonResponse;
import ReDay.common.response.ResponseMessage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Admin Auth", description = "관리자 인증 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/auth")
public class AdminAuthController {

    private final AdminLoginUseCase adminLoginUseCase;

    @Operation(summary = "관리자 로그인", description = "관리자 이메일/비밀번호로 로그인합니다.")
    @PostMapping("/login")
    public CommonResponse<AdminLoginResponse> login(@RequestBody AdminLoginRequest request) {
        AdminLoginResponse response = adminLoginUseCase.execute(request);
        return CommonResponse.success(ResponseMessage.ADMIN_LOGIN_SUCCESS, response);
    }
}
