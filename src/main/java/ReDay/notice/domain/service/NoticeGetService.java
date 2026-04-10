package ReDay.notice.domain.service;

import ReDay.notice.application.exception.NoticeNotFoundException;
import ReDay.notice.domain.entity.Notice;
import ReDay.notice.domain.repository.NoticeRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class NoticeGetService {

    private final NoticeRepository noticeRepository;

    public List<Notice> getNoticeList() {
        return noticeRepository.findAllByOrderByCreatedAtDesc();
    }

    public Notice getNotice(Long noticeId) {
        return noticeRepository.findById(noticeId)
                .orElseThrow(NoticeNotFoundException::new);
    }
}
