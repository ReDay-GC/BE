package ReDay.admin.application.usecase;

import ReDay.notice.application.exception.NoticeNotFoundException;
import ReDay.notice.domain.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class DeleteAdminNoticeUseCase {

    private final NoticeRepository noticeRepository;

    @Transactional
    public void execute(Long noticeId) {
        if (!noticeRepository.existsById(noticeId)) {
            throw new NoticeNotFoundException();
        }
        noticeRepository.deleteById(noticeId);
    }
}
