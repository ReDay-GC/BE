package ReDay.common.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ResponseMessage {

    SUCCESS(0, "요청이 성공적으로 처리되었습니다."),

    // Auth
    LOGIN_SUCCESS(100, "로그인에 성공했습니다."),
    LOGOUT_SUCCESS(101, "로그아웃에 성공했습니다."),
    SIGNUP_SUCCESS(102, "회원가입에 성공했습니다."),

    // Record
    RECORD_CREATED(200, "기록이 저장되었습니다."),
    RECORD_DELETED(201, "기록이 삭제되었습니다."),
    RECORD_FETCHED(202, "기록을 조회했습니다."),

    // Memory
    MEMORY_GENERATED(300, "기억이 생성되었습니다."),
    MEMORY_FETCHED(301, "기억을 조회했습니다."),
    MEMORY_DELETED(302, "기억이 삭제되었습니다."),

    MEMORY_DETAIL_GET_SUCCESS(303, "기억 상세 조회 성공"),
    MEMORY_ANALYSIS_SUCCESS(304, "기억 분석 생성 성공"),
    MEMORY_ANALYSIS_GET_SUCCESS(305, "기억 분석 조회 성공"),

    MEMORY_NOT_FOUND(400, "기억을 찾을 수 없습니다."),
    RECORD_NOT_FOUND(401, "기록을 찾을 수 없습니다."),
    MEMORY_ACCESS_DENIED(401, "해당 기억에 접근할 권한이 없습니다."),
    MEMORY_ANALYSIS_FAILED(500, "기억 분석에 실패했습니다."),

    // Analysis
    ANALYSIS_FETCHED(600, "분석 조회 성공"),

    // Notification
    NOTIFICATION_FETCHED(700, "알림 목록을 조회했습니다."),
    NOTIFICATION_READ(701, "알림을 읽음 처리했습니다."),
    NOTIFICATION_SETTING_FETCHED(702, "알림 설정을 조회했습니다."),
    NOTIFICATION_SETTING_UPDATED(703, "알림 설정이 변경되었습니다."),
    NOTIFICATION_NOT_FOUND(704, "알림을 찾을 수 없습니다.");

    private final int code;
    private final String message;
}
