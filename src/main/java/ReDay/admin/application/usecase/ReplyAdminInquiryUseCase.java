package ReDay.admin.application.usecase;

import ReDay.admin.application.dto.request.AdminInquiryReplyRequest;
import ReDay.inquiry.application.exception.InquiryNotFoundException;
import ReDay.inquiry.domain.entity.Inquiry;
import ReDay.inquiry.domain.repository.InquiryRepository;
import ReDay.notification.domain.entity.NotificationType;
import ReDay.notification.domain.service.NotificationSaveService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class ReplyAdminInquiryUseCase {

    private final InquiryRepository inquiryRepository;
    private final NotificationSaveService notificationSaveService;

    @Transactional
    public void execute(Long inquiryId, AdminInquiryReplyRequest request) {
        Inquiry inquiry = inquiryRepository.findById(inquiryId)
                .orElseThrow(InquiryNotFoundException::new);

        inquiry.reply(request.content());

        notificationSaveService.save(
                inquiry.getUserId(),
                NotificationType.INQUIRY_ANSWER,
                "문의 답변이 도착했어요",
                "'" + inquiry.getTitle() + "' 문의에 답변이 등록되었습니다.",
                inquiryId
        );
    }
}
