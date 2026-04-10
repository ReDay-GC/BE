package ReDay.inquiry.application.usecase;

import ReDay.inquiry.application.dto.request.InquirySaveRequest;
import ReDay.inquiry.application.dto.response.InquirySaveResponse;
import ReDay.inquiry.domain.entity.Inquiry;
import ReDay.inquiry.domain.service.InquirySaveService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SaveInquiryUseCase {

    private final InquirySaveService inquirySaveService;

    public InquirySaveResponse execute(Long userId, InquirySaveRequest request) {
        Inquiry inquiry = Inquiry.builder()
                .userId(userId)
                .title(request.title())
                .content(request.content())
                .build();

        Inquiry saved = inquirySaveService.save(inquiry);
        return new InquirySaveResponse(saved.getId());
    }
}
