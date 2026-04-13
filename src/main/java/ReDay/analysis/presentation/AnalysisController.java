package ReDay.analysis.presentation;

import ReDay.analysis.application.dto.response.MonthlyAnalysisResponse;
import ReDay.analysis.application.usecase.GetMonthlyAnalysisUseCase;
import ReDay.common.response.CommonResponse;
import ReDay.common.response.ResponseMessage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Analysis", description = "월간 통계 분석 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/analysis")
public class AnalysisController {

    private final GetMonthlyAnalysisUseCase getMonthlyAnalysisUseCase;

    @Operation(summary = "월간 분석 조회", description = "월별 기억 추이, 기록 유형 통계, 자주 방문한 장소, 자주 만난 사람, 활동 유형, 월간 인사이트를 조회합니다.")
    @GetMapping("/monthly")
    public CommonResponse<MonthlyAnalysisResponse> getMonthlyAnalysis(
            @AuthenticationPrincipal Long userId,
            @RequestParam int year,
            @RequestParam int month) {
        return CommonResponse.success(ResponseMessage.ANALYSIS_FETCHED,
                getMonthlyAnalysisUseCase.execute(userId, year, month));
    }
}
