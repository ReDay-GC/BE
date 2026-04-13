package ReDay.admin.application.usecase;

import ReDay.admin.application.dto.request.AdminInquiryStatusRequest;
import ReDay.inquiry.application.exception.InquiryNotFoundException;
import ReDay.inquiry.domain.entity.Inquiry;
import ReDay.inquiry.domain.repository.InquiryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class UpdateAdminInquiryStatusUseCase {

    private final InquiryRepository inquiryRepository;

    @Transactional
    public void execute(Long inquiryId, AdminInquiryStatusRequest request) {
        Inquiry inquiry = inquiryRepository.findById(inquiryId)
                .orElseThrow(InquiryNotFoundException::new);

        inquiry.changeStatus(request.status());
    }
}
