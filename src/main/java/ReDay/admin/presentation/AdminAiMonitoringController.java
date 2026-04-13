package ReDay.admin.presentation;

import ReDay.admin.application.dto.response.AdminAiMonitoringLogResponse;
import ReDay.admin.application.dto.response.AdminAiMonitoringStatsResponse;
import ReDay.admin.application.usecase.GetAdminAiMonitoringLogsUseCase;
import ReDay.admin.application.usecase.GetAdminAiMonitoringStatsUseCase;
import ReDay.common.response.CommonResponse;
import ReDay.common.response.ResponseMessage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Admin AI Monitoring", description = "AI 서비스 모니터링 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/ai-monitoring")
public class AdminAiMonitoringController {

    private final GetAdminAiMonitoringStatsUseCase getAdminAiMonitoringStatsUseCase;
    private final GetAdminAiMonitoringLogsUseCase getAdminAiMonitoringLogsUseCase;

    @Operation(summary = "AI 모니터링 통계 조회", description = "총 AI 호출 수, 평균 응답시간, 성공률을 조회합니다.")
    @GetMapping("/stats")
    public CommonResponse<AdminAiMonitoringStatsResponse> getStats() {
        return CommonResponse.success(
                ResponseMessage.ADMIN_AI_MONITORING_STATS_FETCHED,
                getAdminAiMonitoringStatsUseCase.execute()
        );
    }

    @Operation(summary = "AI 처리 로그 조회", description = "AI 처리 로그를 최신순으로 페이징 조회합니다. memory_id가 없으면 '인사이트'로 표시됩니다.")
    @GetMapping("/logs")
    public CommonResponse<Page<AdminAiMonitoringLogResponse>> getLogs(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return CommonResponse.success(
                ResponseMessage.ADMIN_AI_MONITORING_LOGS_FETCHED,
                getAdminAiMonitoringLogsUseCase.execute(pageable)
        );
    }
}
