package ReDay.admin.presentation;

import ReDay.admin.application.dto.response.AdminAiStatsResponse;
import ReDay.admin.application.dto.response.AdminServiceStatsResponse;
import ReDay.admin.application.usecase.GetAdminAiStatsUseCase;
import ReDay.admin.application.usecase.GetAdminServiceStatsUseCase;
import ReDay.common.response.CommonResponse;
import ReDay.common.response.ResponseMessage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
}
