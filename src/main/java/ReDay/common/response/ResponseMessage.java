package ReDay.common.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ResponseMessage {

    SUCCESS("요청이 성공적으로 처리되었습니다."),

    // Auth
    LOGIN_SUCCESS("로그인에 성공했습니다."),
    LOGOUT_SUCCESS("로그아웃에 성공했습니다."),
    SIGNUP_SUCCESS("회원가입에 성공했습니다."),

    // Record
    RECORD_CREATED("기록이 저장되었습니다."),
    RECORD_DELETED("기록이 삭제되었습니다."),
    RECORD_FETCHED("기록을 조회했습니다."),

    // Memory
    MEMORY_GENERATED("기억이 생성되었습니다."),
    MEMORY_FETCHED("기억을 조회했습니다."),
    MEMORY_DELETED("기억이 삭제되었습니다.");

    private final String message;
}
