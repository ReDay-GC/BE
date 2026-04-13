package ReDay.admin.presentation;

import ReDay.admin.application.dto.request.AdminInquiryReplyRequest;
import ReDay.admin.application.dto.request.AdminInquiryStatusRequest;
import ReDay.admin.application.dto.response.AdminInquiryDetailResponse;
import ReDay.admin.application.dto.response.AdminInquiryListResponse;
import ReDay.admin.application.usecase.GetAdminInquiryDetailUseCase;
import ReDay.admin.application.usecase.GetAdminInquiryListUseCase;
import ReDay.admin.application.usecase.ReplyAdminInquiryUseCase;
import ReDay.admin.application.usecase.UpdateAdminInquiryStatusUseCase;
import ReDay.common.response.CommonResponse;
import ReDay.common.response.ResponseMessage;
import ReDay.inquiry.domain.entity.InquiryStatus;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Admin Inquiry", description = "관리자 문의사항 관리 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/inquiries")
public class AdminInquiryController {

    private final GetAdminInquiryListUseCase getAdminInquiryListUseCase;
    private final GetAdminInquiryDetailUseCase getAdminInquiryDetailUseCase;
    private final ReplyAdminInquiryUseCase replyAdminInquiryUseCase;
    private final UpdateAdminInquiryStatusUseCase updateAdminInquiryStatusUseCase;

    @Operation(summary = "문의 목록 조회", description = "전체 문의 목록을 조회합니다. status 필터 및 키워드 검색 가능합니다.")
    @GetMapping
    public CommonResponse<List<AdminInquiryListResponse>> getInquiryList(
            @RequestParam(required = false) InquiryStatus status,
            @RequestParam(required = false) String keyword) {
        return CommonResponse.success(
                ResponseMessage.ADMIN_INQUIRY_LIST_FETCHED,
                getAdminInquiryListUseCase.execute(status, keyword)
        );
    }

    @Operation(summary = "문의 상세 조회", description = "문의 내용과 답변 상태를 조회합니다.")
    @GetMapping("/{inquiryId}")
    public CommonResponse<AdminInquiryDetailResponse> getInquiryDetail(
            @PathVariable Long inquiryId) {
        return CommonResponse.success(
                ResponseMessage.ADMIN_INQUIRY_DETAIL_FETCHED,
                getAdminInquiryDetailUseCase.execute(inquiryId)
        );
    }

    @Operation(summary = "문의 답변 등록", description = "문의에 답변을 등록하고 상태를 ANSWERED로 변경합니다.")
    @PostMapping("/{inquiryId}/reply")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void replyInquiry(
            @PathVariable Long inquiryId,
            @Valid @RequestBody AdminInquiryReplyRequest request) {
        replyAdminInquiryUseCase.execute(inquiryId, request);
    }

    @Operation(summary = "문의 상태 변경", description = "문의 상태를 WAITING 또는 ANSWERED로 변경합니다.")
    @PatchMapping("/{inquiryId}/status")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateInquiryStatus(
            @PathVariable Long inquiryId,
            @Valid @RequestBody AdminInquiryStatusRequest request) {
        updateAdminInquiryStatusUseCase.execute(inquiryId, request);
    }
}
