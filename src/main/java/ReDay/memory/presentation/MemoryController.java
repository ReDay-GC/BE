package ReDay.memory.presentation;

import ReDay.common.response.CommonResponse;
import ReDay.common.response.ResponseMessage;
import ReDay.memory.application.dto.request.MemorySaveRequest;
import ReDay.memory.application.dto.request.MemorySearchRequest;
import ReDay.memory.application.dto.response.MemoryDetailResponse;
import ReDay.memory.application.dto.response.MemoryListResponse;
import ReDay.memory.application.dto.response.MemorySearchResponse;
import ReDay.memory.application.dto.response.MemoryCalendarResponse;
import ReDay.memory.application.dto.response.MemoryTagListResponse;
import ReDay.memory.application.usecase.DeleteMemoryUseCase;
import ReDay.memory.application.usecase.CreateMemoryUseCase;
import ReDay.memory.application.usecase.GetAllTagsUseCase;
import ReDay.memory.application.usecase.GetMemoryByDateUseCase;
import ReDay.memory.application.usecase.GetMemoryCalendarUseCase;
import ReDay.memory.application.usecase.GetMemoryDetailUseCase;
import ReDay.memory.application.usecase.GetMemoryListUseCase;
import ReDay.memory.application.usecase.SearchMemoryByLocationUseCase;
import ReDay.memory.application.usecase.SearchMemoryByTagUseCase;
import ReDay.memory.application.usecase.SearchMemoryUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Memory", description = "기억 관리 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/memories")
public class MemoryController {

    private final DeleteMemoryUseCase deleteMemoryUseCase;
    private final GetMemoryDetailUseCase getMemoryDetailUseCase;
    private final CreateMemoryUseCase createMemoryUseCase;
    private final GetMemoryListUseCase getMemoryListUseCase;
    private final SearchMemoryUseCase searchMemoryUseCase;
    private final GetMemoryCalendarUseCase getMemoryCalendarUseCase;
    private final GetMemoryByDateUseCase getMemoryByDateUseCase;
    private final GetAllTagsUseCase getAllTagsUseCase;
    private final SearchMemoryByTagUseCase searchMemoryByTagUseCase;
    private final SearchMemoryByLocationUseCase searchMemoryByLocationUseCase;

    @Operation(summary = "기억 삭제", description = "기억 ID를 기준으로 기억을 삭제합니다.")
    @DeleteMapping("/{memoryId}")
    public CommonResponse<Void> deleteMemory(@PathVariable Long memoryId) {
        deleteMemoryUseCase.execute(memoryId);

        return CommonResponse.success(ResponseMessage.MEMORY_DELETED, null);
    }

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
    public CommonResponse<List<MemoryListResponse>> getMemoryList() {
        List<MemoryListResponse> response = getMemoryListUseCase.execute();

        return CommonResponse.success(
                ResponseMessage.MEMORY_FETCHED,
                response
        );
    }

    @Operation(summary = "기억 검색", description = "키워드 기준으로 기억을 검색합니다.")
    @GetMapping("/search")
    public CommonResponse<List<MemorySearchResponse>> searchMemory(
            @RequestParam String keyword
    ) {
        List<MemorySearchResponse> response = searchMemoryUseCase.execute(
                new MemorySearchRequest(keyword)
        );

        return CommonResponse.success(
                ResponseMessage.MEMORY_FETCHED,
                response
        );
    }

    @Operation(summary = "캘린더 조회", description = "특정 연월에 기억이 있는 날짜 목록을 조회합니다.")
    @GetMapping("/calendar")
    public CommonResponse<MemoryCalendarResponse> getMemoryCalendar(
            @RequestParam int year,
            @RequestParam int month
    ) {
        MemoryCalendarResponse response = getMemoryCalendarUseCase.execute(year, month);

        return CommonResponse.success(
                ResponseMessage.MEMORY_FETCHED,
                response
        );
    }

    @Operation(summary = "날짜별 기억 조회", description = "특정 날짜의 기억 목록을 조회합니다.")
    @GetMapping("/date")
    public CommonResponse<List<MemoryListResponse>> getMemoryByDate(
            @RequestParam LocalDate date
    ) {
        List<MemoryListResponse> response = getMemoryByDateUseCase.execute(date);

        return CommonResponse.success(
                ResponseMessage.MEMORY_FETCHED,
                response
        );
    }

    @Operation(summary = "전체 태그 목록 조회", description = "등록된 모든 태그 목록을 조회합니다.")
    @GetMapping("/tags")
    public CommonResponse<MemoryTagListResponse> getAllTags() {
        MemoryTagListResponse response = getAllTagsUseCase.execute();

        return CommonResponse.success(
                ResponseMessage.MEMORY_FETCHED,
                response
        );
    }

    @Operation(summary = "태그별 기억 검색", description = "특정 태그가 포함된 기억 목록을 조회합니다.")
    @GetMapping("/search/tag")
    public CommonResponse<List<MemorySearchResponse>> searchMemoryByTag(
            @RequestParam String tagName
    ) {
        List<MemorySearchResponse> response = searchMemoryByTagUseCase.execute(tagName);

        return CommonResponse.success(
                ResponseMessage.MEMORY_FETCHED,
                response
        );
    }

    @Operation(summary = "장소별 기억 검색", description = "특정 장소명으로 기억 목록을 조회합니다.")
    @GetMapping("/search/location")
    public CommonResponse<List<MemorySearchResponse>> searchMemoryByLocation(
            @RequestParam String location
    ) {
        List<MemorySearchResponse> response = searchMemoryByLocationUseCase.execute(location);

        return CommonResponse.success(
                ResponseMessage.MEMORY_FETCHED,
                response
        );
    }
}
