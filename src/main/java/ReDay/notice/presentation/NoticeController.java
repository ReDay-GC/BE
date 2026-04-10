package ReDay.notice.presentation;

import ReDay.common.response.CommonResponse;
import ReDay.common.response.ResponseMessage;
import ReDay.notice.application.dto.response.NoticeDetailResponse;
import ReDay.notice.application.dto.response.NoticeListResponse;
import ReDay.notice.application.usecase.GetNoticeDetailUseCase;
import ReDay.notice.application.usecase.GetNoticeListUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Notice", description = "공지사항 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/notices")
public class NoticeController {

    private final GetNoticeListUseCase getNoticeListUseCase;
    private final GetNoticeDetailUseCase getNoticeDetailUseCase;

    @Operation(summary = "공지사항 목록 조회", description = "공지사항 목록을 최신순으로 조회합니다.")
    @GetMapping
    public CommonResponse<List<NoticeListResponse>> getNoticeList() {
        List<NoticeListResponse> response = getNoticeListUseCase.execute();
        return CommonResponse.success(ResponseMessage.NOTICE_LIST_FETCHED, response);
    }

    @Operation(summary = "공지사항 상세 조회", description = "공지사항 상세 내용을 조회합니다.")
    @GetMapping("/{noticeId}")
    public CommonResponse<NoticeDetailResponse> getNoticeDetail(
            @PathVariable Long noticeId
    ) {
        NoticeDetailResponse response = getNoticeDetailUseCase.execute(noticeId);
        return CommonResponse.success(ResponseMessage.NOTICE_DETAIL_FETCHED, response);
    }
}
