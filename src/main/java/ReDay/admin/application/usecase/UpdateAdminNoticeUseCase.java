package ReDay.admin.application.usecase;

import ReDay.admin.application.dto.request.AdminNoticeUpdateRequest;
import ReDay.notice.application.exception.NoticeNotFoundException;
import ReDay.notice.domain.entity.Notice;
import ReDay.notice.domain.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class UpdateAdminNoticeUseCase {

    private final NoticeRepository noticeRepository;

    @Transactional
    public void execute(Long noticeId, AdminNoticeUpdateRequest request) {
        Notice notice = noticeRepository.findById(noticeId)
                .orElseThrow(NoticeNotFoundException::new);

        notice.update(request.title(), request.content(), request.isPublic());
    }
}
