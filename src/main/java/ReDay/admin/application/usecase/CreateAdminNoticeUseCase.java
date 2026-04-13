package ReDay.admin.application.usecase;

import ReDay.admin.application.dto.request.AdminNoticeSaveRequest;
import ReDay.notice.domain.entity.Notice;
import ReDay.notice.domain.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class CreateAdminNoticeUseCase {

    private final NoticeRepository noticeRepository;

    @Transactional
    public void execute(AdminNoticeSaveRequest request) {
        noticeRepository.save(Notice.builder()
                .title(request.title())
                .content(request.content())
                .isPublic(request.isPublic())
                .build());
    }
}
