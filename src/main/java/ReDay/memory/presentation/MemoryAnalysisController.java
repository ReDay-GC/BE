
package ReDay.memory.presentation;

import ReDay.common.response.CommonResponse;
import ReDay.common.response.ResponseMessage;
import ReDay.memory.application.dto.response.MemoryAnalysisResponse;
import ReDay.memory.application.usecase.AnalyzeMemoryUseCase;
import ReDay.memory.application.usecase.GetMemoryAnalysisUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Memory Analysis", description = "기억 분석 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/memories")
public class MemoryAnalysisController {

    private final AnalyzeMemoryUseCase analyzeMemoryUseCase;
    private final GetMemoryAnalysisUseCase getMemoryAnalysisUseCase;

    @Operation(summary = "기억 분석 생성", description = "기억 ID를 기준으로 기억 분석 결과를 생성합니다.")
    @PostMapping("/{memoryId}/analysis")
    public CommonResponse<MemoryAnalysisResponse> analyzeMemory(
            @AuthenticationPrincipal Long userId,
            @PathVariable Long memoryId) {
        MemoryAnalysisResponse response = analyzeMemoryUseCase.execute(userId, memoryId);

        return CommonResponse.success(
                ResponseMessage.MEMORY_ANALYSIS_SUCCESS,
                response
        );
    }

    @Operation(summary = "기억 분석 조회", description = "기억 ID를 기준으로 기억 분석 결과를 조회합니다.")
    @GetMapping("/{memoryId}/analysis")
    public CommonResponse<MemoryAnalysisResponse> getMemoryAnalysis(@PathVariable Long memoryId) {
        MemoryAnalysisResponse response = getMemoryAnalysisUseCase.execute(memoryId);

        return CommonResponse.success(
                ResponseMessage.MEMORY_ANALYSIS_GET_SUCCESS,
                response
        );
    }
}
