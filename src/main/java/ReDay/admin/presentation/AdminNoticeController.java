package ReDay.admin.presentation;

import ReDay.admin.application.dto.request.AdminNoticeSaveRequest;
import ReDay.admin.application.dto.request.AdminNoticeUpdateRequest;
import ReDay.admin.application.dto.response.AdminNoticeListResponse;
import ReDay.admin.application.usecase.CreateAdminNoticeUseCase;
import ReDay.admin.application.usecase.DeleteAdminNoticeUseCase;
import ReDay.admin.application.usecase.GetAdminNoticeListUseCase;
import ReDay.admin.application.usecase.UpdateAdminNoticeUseCase;
import ReDay.common.response.CommonResponse;
import ReDay.common.response.ResponseMessage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Admin Notice", description = "관리자 공지사항 관리 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/notices")
public class AdminNoticeController {

    private final GetAdminNoticeListUseCase getAdminNoticeListUseCase;
    private final CreateAdminNoticeUseCase createAdminNoticeUseCase;
    private final UpdateAdminNoticeUseCase updateAdminNoticeUseCase;
    private final DeleteAdminNoticeUseCase deleteAdminNoticeUseCase;

    @Operation(summary = "공지 목록 조회", description = "전체 공지 목록을 조회합니다. 비공개 포함 전체 반환됩니다.")
    @GetMapping
    public CommonResponse<List<AdminNoticeListResponse>> getNoticeList() {
        return CommonResponse.success(
                ResponseMessage.ADMIN_NOTICE_LIST_FETCHED,
                getAdminNoticeListUseCase.execute()
        );
    }

    @Operation(summary = "공지 등록", description = "새 공지사항을 등록합니다.")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createNotice(@Valid @RequestBody AdminNoticeSaveRequest request) {
        createAdminNoticeUseCase.execute(request);
    }

    @Operation(summary = "공지 수정", description = "공지사항 제목, 내용, 공개여부를 수정합니다.")
    @PutMapping("/{noticeId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateNotice(
            @PathVariable Long noticeId,
            @Valid @RequestBody AdminNoticeUpdateRequest request) {
        updateAdminNoticeUseCase.execute(noticeId, request);
    }

    @Operation(summary = "공지 삭제", description = "공지사항을 삭제합니다.")
    @DeleteMapping("/{noticeId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteNotice(@PathVariable Long noticeId) {
        deleteAdminNoticeUseCase.execute(noticeId);
    }
}
