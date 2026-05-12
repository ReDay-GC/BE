package ReDay.admin.presentation;

import ReDay.admin.application.dto.response.AdminAiStatsResponse;
import ReDay.admin.application.dto.response.AdminMemoryListResponse;
import ReDay.admin.application.dto.response.AdminRecordListResponse;
import ReDay.admin.application.dto.response.AdminServiceStatsResponse;
import ReDay.admin.application.usecase.GetAdminAiStatsUseCase;
import ReDay.admin.application.usecase.GetAdminAllMemoriesUseCase;
import ReDay.admin.application.usecase.GetAdminAllRecordsUseCase;
import ReDay.admin.application.usecase.GetAdminServiceStatsUseCase;
import ReDay.admin.application.usecase.GetAdminTodayMemoriesUseCase;
import ReDay.common.response.CommonResponse;
import ReDay.common.response.ResponseMessage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Admin Stats", description = "관리자 서비스 통계 및 AI 모니터링 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/stats")
public class AdminStatsController {

    private final GetAdminServiceStatsUseCase getAdminServiceStatsUseCase;
    private final GetAdminAiStatsUseCase getAdminAiStatsUseCase;
    private final GetAdminTodayMemoriesUseCase getAdminTodayMemoriesUseCase;
    private final GetAdminAllMemoriesUseCase getAdminAllMemoriesUseCase;
    private final GetAdminAllRecordsUseCase getAdminAllRecordsUseCase;

    @Operation(summary = "서비스 통계 조회", description = "오늘/전체 기억 수, 전체 기록 수, 기록 유형별 분포를 조회합니다.")
    @GetMapping("/service")
    public CommonResponse<AdminServiceStatsResponse> getServiceStats() {
        AdminServiceStatsResponse response = getAdminServiceStatsUseCase.execute();
        return CommonResponse.success(ResponseMessage.ADMIN_SERVICE_STATS_FETCHED, response);
    }

    @Operation(summary = "AI 서비스 모니터링", description = "총 AI 요청 수, 평균 응답시간, 성공률, AI 처리 로그를 조회합니다.")
    @GetMapping("/ai")
    public CommonResponse<AdminAiStatsResponse> getAiStats() {
        AdminAiStatsResponse response = getAdminAiStatsUseCase.execute();
        return CommonResponse.success(ResponseMessage.ADMIN_AI_STATS_FETCHED, response);
    }

    @Operation(summary = "오늘 생성 기억 목록 조회", description = "오늘 생성된 전체 사용자의 기억 목록을 조회합니다.")
    @GetMapping("/memories/today")
    public CommonResponse<List<AdminMemoryListResponse>> getTodayMemories() {
        List<AdminMemoryListResponse> response = getAdminTodayMemoriesUseCase.execute();
        return CommonResponse.success(ResponseMessage.ADMIN_TODAY_MEMORIES_FETCHED, response);
    }

    @Operation(summary = "전체 기억 목록 조회", description = "전체 사용자의 기억 목록을 조회합니다.")
    @GetMapping("/memories")
    public CommonResponse<List<AdminMemoryListResponse>> getAllMemories() {
        List<AdminMemoryListResponse> response = getAdminAllMemoriesUseCase.execute();
        return CommonResponse.success(ResponseMessage.ADMIN_ALL_MEMORIES_FETCHED, response);
    }

    @Operation(summary = "전체 기록 목록 조회", description = "전체 사용자의 기록 목록을 조회합니다.")
    @GetMapping("/records")
    public CommonResponse<List<AdminRecordListResponse>> getAllRecords() {
        List<AdminRecordListResponse> response = getAdminAllRecordsUseCase.execute();
        return CommonResponse.success(ResponseMessage.ADMIN_ALL_RECORDS_FETCHED, response);
    }
}
