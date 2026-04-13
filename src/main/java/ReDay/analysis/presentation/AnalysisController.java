package ReDay.analysis.presentation;

import ReDay.analysis.application.dto.request.MonthlyInsightReceiveRequest;
import ReDay.analysis.application.dto.response.MonthlyAnalysisResponse;
import ReDay.analysis.application.usecase.GetMonthlyAnalysisUseCase;
import ReDay.analysis.application.usecase.SaveMonthlyInsightUseCase;
import ReDay.common.response.CommonResponse;
import ReDay.common.response.ResponseMessage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@Tag(name = "Analysis", description = "월간 통계 분석 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/analysis")
public class AnalysisController {

    private final GetMonthlyAnalysisUseCase getMonthlyAnalysisUseCase;
    private final SaveMonthlyInsightUseCase saveMonthlyInsightUseCase;

    @Value("${internal.api-key}")
    private String internalApiKey;

    @Operation(summary = "월간 분석 조회", description = "월별 기억 추이, 기록 유형 통계, 자주 방문한 장소, 자주 만난 사람, 활동 유형, 월간 인사이트를 조회합니다.")
    @GetMapping("/monthly")
    public CommonResponse<MonthlyAnalysisResponse> getMonthlyAnalysis(
            @AuthenticationPrincipal Long userId,
            @RequestParam int year,
            @RequestParam int month) {
        return CommonResponse.success(ResponseMessage.ANALYSIS_FETCHED,
                getMonthlyAnalysisUseCase.execute(userId, year, month));
    }

    @Operation(summary = "AI 인사이트 저장 (AI 서버 전용)", description = "AI 서버가 생성한 월간 인사이트를 저장합니다. X-Internal-Key 헤더 인증 필요.")
    @PostMapping("/monthly/insight")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void saveMonthlyInsight(
            @RequestHeader("X-Internal-Key") String key,
            @Valid @RequestBody MonthlyInsightReceiveRequest request) {
        if (!internalApiKey.equals(key)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid internal key");
        }
        saveMonthlyInsightUseCase.execute(request);
    }
}
