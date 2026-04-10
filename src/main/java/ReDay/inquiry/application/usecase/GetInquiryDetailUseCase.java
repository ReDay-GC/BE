package ReDay.inquiry.application.usecase;

import ReDay.inquiry.application.dto.response.InquiryDetailResponse;
import ReDay.inquiry.domain.entity.Inquiry;
import ReDay.inquiry.domain.service.InquiryGetService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetInquiryDetailUseCase {

    private final InquiryGetService inquiryGetService;

    public InquiryDetailResponse execute(Long inquiryId, Long userId) {
        Inquiry inquiry = inquiryGetService.getInquiry(inquiryId, userId);
        return new InquiryDetailResponse(
                inquiry.getId(),
                inquiry.getTitle(),
                inquiry.getContent(),
                inquiry.getStatus(),
                inquiry.getReplyContent(),
                inquiry.getRepliedAt(),
                inquiry.getCreatedAt()
        );
    }
}
