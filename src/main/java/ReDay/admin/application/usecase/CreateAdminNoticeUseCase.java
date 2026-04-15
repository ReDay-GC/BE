package ReDay.admin.application.usecase;

import ReDay.admin.application.dto.request.AdminNoticeSaveRequest;
import ReDay.notice.domain.entity.Notice;
import ReDay.notice.domain.repository.NoticeRepository;
import ReDay.notification.domain.entity.NotificationType;
import ReDay.notification.domain.service.NotificationSaveService;
import ReDay.user.domain.repository.UserRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class CreateAdminNoticeUseCase {

    private final NoticeRepository noticeRepository;
    private final NotificationSaveService notificationSaveService;
    private final UserRepository userRepository;

    @Transactional
    public void execute(AdminNoticeSaveRequest request) {
        Notice notice = noticeRepository.save(Notice.builder()
                .title(request.title())
                .content(request.content())
                .isPublic(request.isPublic())
                .build());

        if (request.isPublic()) {
            List<Long> allUserIds = userRepository.findAll().stream()
                    .map(user -> user.getId())
                    .toList();
            notificationSaveService.saveAll(
                    allUserIds,
                    NotificationType.NOTICE,
                    "새 공지사항이 등록되었어요",
                    request.title(),
                    notice.getId()
            );
        }
    }
}
