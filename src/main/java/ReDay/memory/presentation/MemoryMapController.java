package ReDay.memory.presentation;

import ReDay.common.response.CommonResponse;
import ReDay.common.response.ResponseMessage;
import ReDay.memory.application.dto.response.MemoryListResponse;
import ReDay.memory.application.dto.response.MemoryMapPinResponse;
import ReDay.memory.application.usecase.GetMemoryByLocationUseCase;
import ReDay.memory.application.usecase.GetMemoryMapUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Memory Map", description = "기억 지도 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/memories/map")
public class MemoryMapController {

    private final GetMemoryMapUseCase getMemoryMapUseCase;
    private final GetMemoryByLocationUseCase getMemoryByLocationUseCase;

    @Operation(summary = "지도 핀 목록 조회", description = "기억이 있는 장소 목록과 기억 수를 조회합니다.")
    @GetMapping
    public CommonResponse<List<MemoryMapPinResponse>> getMemoryMap(
            @AuthenticationPrincipal Long userId) {
        List<MemoryMapPinResponse> response = getMemoryMapUseCase.execute(userId);

        return CommonResponse.success(
                ResponseMessage.MEMORY_FETCHED,
                response
        );
    }

    @Operation(summary = "장소별 기억 조회", description = "특정 장소의 기억 목록을 조회합니다.")
    @GetMapping("/location")
    public CommonResponse<List<MemoryListResponse>> getMemoryByLocation(
            @AuthenticationPrincipal Long userId,
            @RequestParam String location
    ) {
        List<MemoryListResponse> response = getMemoryByLocationUseCase.execute(userId, location);

        return CommonResponse.success(
                ResponseMessage.MEMORY_FETCHED,
                response
        );
    }
}
