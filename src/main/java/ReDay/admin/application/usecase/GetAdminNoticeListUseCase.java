package ReDay.admin.application.usecase;

import ReDay.admin.application.dto.response.AdminNoticeListResponse;
import ReDay.notice.domain.service.NoticeGetService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetAdminNoticeListUseCase {

    private final NoticeGetService noticeGetService;

    public List<AdminNoticeListResponse> execute() {
        return noticeGetService.getAllNoticeList().stream()
                .map(n -> new AdminNoticeListResponse(
                        n.getId(),
                        n.getTitle(),
                        n.isPublic(),
                        n.getCreatedAt()
                ))
                .toList();
    }
}
