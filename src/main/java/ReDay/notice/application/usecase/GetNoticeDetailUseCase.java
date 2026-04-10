package ReDay.notice.application.usecase;

import ReDay.notice.application.dto.response.NoticeDetailResponse;
import ReDay.notice.domain.entity.Notice;
import ReDay.notice.domain.service.NoticeGetService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetNoticeDetailUseCase {

    private final NoticeGetService noticeGetService;

    public NoticeDetailResponse execute(Long noticeId) {
        Notice notice = noticeGetService.getNotice(noticeId);
        return new NoticeDetailResponse(
                notice.getId(),
                notice.getTitle(),
                notice.getContent(),
                notice.getCreatedAt()
        );
    }
}
