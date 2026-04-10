package ReDay.notice.application.usecase;

import ReDay.notice.application.dto.response.NoticeListResponse;
import ReDay.notice.domain.entity.Notice;
import ReDay.notice.domain.service.NoticeGetService;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetNoticeListUseCase {

    private static final int NEW_NOTICE_DAYS = 7;
    private static final int PREVIEW_MAX_LENGTH = 50;

    private final NoticeGetService noticeGetService;

    public List<NoticeListResponse> execute() {
        return noticeGetService.getNoticeList()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    private NoticeListResponse toResponse(Notice notice) {
        String preview = notice.getContent().length() > PREVIEW_MAX_LENGTH
                ? notice.getContent().substring(0, PREVIEW_MAX_LENGTH) + "..."
                : notice.getContent();

        boolean isNew = notice.getCreatedAt().isAfter(LocalDateTime.now().minusDays(NEW_NOTICE_DAYS));

        return new NoticeListResponse(
                notice.getId(),
                notice.getTitle(),
                preview,
                isNew,
                notice.getCreatedAt()
        );
    }
}
