package ReDay.inquiry.presentation;

import ReDay.common.response.CommonResponse;
import ReDay.common.response.ResponseMessage;
import ReDay.inquiry.application.dto.request.InquirySaveRequest;
import ReDay.inquiry.application.dto.response.InquiryDetailResponse;
import ReDay.inquiry.application.dto.response.InquiryListResponse;
import ReDay.inquiry.application.dto.response.InquirySaveResponse;
import ReDay.inquiry.application.usecase.GetInquiryDetailUseCase;
import ReDay.inquiry.application.usecase.GetInquiryListUseCase;
import ReDay.inquiry.application.usecase.SaveInquiryUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Inquiry", description = "문의사항 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/inquiries")
public class InquiryController {

    private final SaveInquiryUseCase saveInquiryUseCase;
    private final GetInquiryListUseCase getInquiryListUseCase;
    private final GetInquiryDetailUseCase getInquiryDetailUseCase;

    @Operation(summary = "문의 작성", description = "문의사항을 작성합니다.")
    @PostMapping
    public CommonResponse<InquirySaveResponse> saveInquiry(
            @AuthenticationPrincipal Long userId,
            @RequestBody InquirySaveRequest request
    ) {
        InquirySaveResponse response = saveInquiryUseCase.execute(userId, request);
        return CommonResponse.success(ResponseMessage.INQUIRY_CREATED, response);
    }

    @Operation(summary = "문의 목록 조회", description = "내 문의 목록을 최신순으로 조회합니다.")
    @GetMapping
    public CommonResponse<List<InquiryListResponse>> getInquiryList(
            @AuthenticationPrincipal Long userId
    ) {
        List<InquiryListResponse> response = getInquiryListUseCase.execute(userId);
        return CommonResponse.success(ResponseMessage.INQUIRY_LIST_FETCHED, response);
    }

    @Operation(summary = "문의 상세 조회", description = "문의사항 상세 내용과 답변을 조회합니다.")
    @GetMapping("/{inquiryId}")
    public CommonResponse<InquiryDetailResponse> getInquiryDetail(
            @AuthenticationPrincipal Long userId,
            @PathVariable Long inquiryId
    ) {
        InquiryDetailResponse response = getInquiryDetailUseCase.execute(inquiryId, userId);
        return CommonResponse.success(ResponseMessage.INQUIRY_DETAIL_FETCHED, response);
    }
}
