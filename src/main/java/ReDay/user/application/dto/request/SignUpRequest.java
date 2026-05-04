package ReDay.user.application.dto.request;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record SignUpRequest(

        @NotBlank(message = "이름 또는 닉네임을 입력해주세요.")
        String name,

        @NotBlank(message = "아이디를 입력해주세요.")
        @Size(min = 6, message = "아이디는 6자 이상이어야 합니다.")
        String email,

        @NotBlank(message = "비밀번호를 입력해주세요.")
        @Size(min = 6, message = "비밀번호는 6자 이상이어야 합니다.")
        String password,

        @NotBlank(message = "비밀번호 확인을 입력해주세요.")
        String passwordConfirm,

        @AssertTrue(message = "이용약관에 동의해주세요.")
        boolean termsAgreed,

        @AssertTrue(message = "개인정보 처리방침에 동의해주세요.")
        boolean privacyAgreed
) {
}
