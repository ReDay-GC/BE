package ReDay.memory.presentation;

import ReDay.common.response.CommonResponse;
import ReDay.common.response.ResponseMessage;
import ReDay.memory.application.dto.request.MemorySaveRequest;
import ReDay.memory.application.dto.request.MemorySearchRequest;
import ReDay.memory.application.dto.response.MemoryDetailResponse;
import ReDay.memory.application.dto.response.MemoryListResponse;
import ReDay.memory.application.usecase.CreateMemoryUseCase;
import ReDay.memory.application.usecase.GetMemoryDetailUseCase;
import ReDay.memory.application.usecase.GetMemoryListUseCase;
import ReDay.memory.application.usecase.SearchMemoryUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Memory", description = "기억 관리 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/memories")
public class MemoryController {

    private final GetMemoryDetailUseCase getMemoryDetailUseCase;
    private final CreateMemoryUseCase createMemoryUseCase;
    private final GetMemoryListUseCase getMemoryListUseCase;
    private final SearchMemoryUseCase searchMemoryUseCase;

    @Operation(summary = "기억 상세 조회", description = "기억 ID를 기준으로 기억 상세 정보를 조회합니다.")
    @GetMapping("/{memoryId}")
    public CommonResponse<MemoryDetailResponse> getMemoryDetail(@PathVariable Long memoryId) {
        MemoryDetailResponse response = getMemoryDetailUseCase.execute(memoryId);

        return CommonResponse.success(
                ResponseMessage.MEMORY_DETAIL_GET_SUCCESS,
                response
        );
    }

    @Operation(summary = "기억 생성", description = "새로운 기억을 생성합니다.")
    @PostMapping
    public CommonResponse<MemoryListResponse> createMemory(@RequestBody MemorySaveRequest request) {
        MemoryListResponse response = createMemoryUseCase.execute(request);

        return CommonResponse.success(
                ResponseMessage.MEMORY_GENERATED,
                response
        );
    }

    @Operation(summary = "기억 목록 조회", description = "전체 기억 목록을 조회합니다.")
    @GetMapping
    public CommonResponse<java.util.List<MemoryListResponse>> getMemoryList() {
        java.util.List<MemoryListResponse> response = getMemoryListUseCase.execute();

        return CommonResponse.success(
                ResponseMessage.MEMORY_FETCHED,
                response
        );
    }

    @Operation(summary = "기억 검색", description = "키워드, 감정, 날짜 범위 기준으로 기억을 검색합니다.")
    @GetMapping("/search")
    public CommonResponse<java.util.List<ReDay.memory.application.dto.response.MemorySearchResponse>> searchMemory(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String emotion,
            @RequestParam(required = false) java.time.LocalDate startDate,
            @RequestParam(required = false) java.time.LocalDate endDate
    ) {
        ReDay.memory.application.dto.request.MemorySearchRequest request =
                new ReDay.memory.application.dto.request.MemorySearchRequest(
                        keyword,
                        emotion,
                        startDate,
                        endDate
                );

        java.util.List<ReDay.memory.application.dto.response.MemorySearchResponse> response =
                searchMemoryUseCase.execute(request);

        return CommonResponse.success(
                ResponseMessage.MEMORY_FETCHED,
                response
        );
    }
}
