package ReDay.admin.presentation;

import ReDay.admin.application.dto.request.AdminUpdateUserRequest;
import ReDay.admin.application.dto.response.AdminUserDetailResponse;
import ReDay.admin.application.dto.response.AdminUserListResponse;
import ReDay.admin.application.dto.response.AdminUserMemoryResponse;
import ReDay.admin.application.dto.response.AdminUserStatsResponse;
import ReDay.admin.application.usecase.DeleteAdminUserUseCase;
import ReDay.admin.application.usecase.GetAdminUserDetailUseCase;
import ReDay.admin.application.usecase.GetAdminUserListUseCase;
import ReDay.admin.application.usecase.GetAdminUserMemoriesUseCase;
import ReDay.admin.application.usecase.GetAdminUserStatsUseCase;
import ReDay.admin.application.usecase.UpdateAdminUserUseCase;
import ReDay.common.response.CommonResponse;
import ReDay.common.response.ResponseMessage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Admin User", description = "관리자 회원 관리 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/users")
public class AdminUserController {

    private final GetAdminUserStatsUseCase getAdminUserStatsUseCase;
    private final GetAdminUserListUseCase getAdminUserListUseCase;
    private final GetAdminUserDetailUseCase getAdminUserDetailUseCase;
    private final GetAdminUserMemoriesUseCase getAdminUserMemoriesUseCase;
    private final UpdateAdminUserUseCase updateAdminUserUseCase;
    private final DeleteAdminUserUseCase deleteAdminUserUseCase;

    @Operation(summary = "전체/신규 사용자 수 조회", description = "전체 사용자 수와 오늘 신규 가입자 수를 조회합니다.")
    @GetMapping("/stats")
    public CommonResponse<AdminUserStatsResponse> getUserStats() {
        AdminUserStatsResponse response = getAdminUserStatsUseCase.execute();
        return CommonResponse.success(ResponseMessage.ADMIN_USER_STATS_FETCHED, response);
    }

    @Operation(summary = "회원 목록 조회 / 회원 검색", description = "전체 회원 목록을 조회합니다. search 파라미터로 이름 검색 가능합니다.")
    @GetMapping
    public CommonResponse<List<AdminUserListResponse>> getUserList(
            @RequestParam(required = false) String search
    ) {
        List<AdminUserListResponse> response = getAdminUserListUseCase.execute(search);
        return CommonResponse.success(ResponseMessage.ADMIN_USER_LIST_FETCHED, response);
    }

    @Operation(summary = "회원 상세 조회", description = "회원의 프로필, 기록 통계, 기억 목록을 조회합니다.")
    @GetMapping("/{userId}")
    public CommonResponse<AdminUserDetailResponse> getUserDetail(@PathVariable Long userId) {
        AdminUserDetailResponse response = getAdminUserDetailUseCase.execute(userId);
        return CommonResponse.success(ResponseMessage.ADMIN_USER_DETAIL_FETCHED, response);
    }

    @Operation(summary = "사용자 기억 데이터 조회", description = "특정 사용자의 기억 목록만 조회합니다.")
    @GetMapping("/{userId}/memories")
    public CommonResponse<List<AdminUserMemoryResponse>> getUserMemories(@PathVariable Long userId) {
        List<AdminUserMemoryResponse> response = getAdminUserMemoriesUseCase.execute(userId);
        return CommonResponse.success(ResponseMessage.ADMIN_USER_MEMORIES_FETCHED, response);
    }

    @Operation(summary = "회원 정보 수정", description = "회원 이름을 수정합니다.")
    @PutMapping("/{userId}")
    public CommonResponse<AdminUserListResponse> updateUser(
            @PathVariable Long userId,
            @RequestBody AdminUpdateUserRequest request
    ) {
        AdminUserListResponse response = updateAdminUserUseCase.execute(userId, request);
        return CommonResponse.success(ResponseMessage.ADMIN_USER_UPDATED, response);
    }

    @Operation(summary = "회원 계정 삭제", description = "회원 계정을 삭제합니다.")
    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable Long userId) {
        deleteAdminUserUseCase.execute(userId);
    }
}
