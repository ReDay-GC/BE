package ReDay.inquiry.application.usecase;

import ReDay.inquiry.application.dto.response.InquiryListResponse;
import ReDay.inquiry.domain.entity.Inquiry;
import ReDay.inquiry.domain.service.InquiryGetService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetInquiryListUseCase {

    private static final int PREVIEW_MAX_LENGTH = 50;

    private final InquiryGetService inquiryGetService;

    public List<InquiryListResponse> execute(Long userId) {
        return inquiryGetService.getInquiryList(userId)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    private InquiryListResponse toResponse(Inquiry inquiry) {
        String preview = inquiry.getContent().length() > PREVIEW_MAX_LENGTH
                ? inquiry.getContent().substring(0, PREVIEW_MAX_LENGTH) + "..."
                : inquiry.getContent();

        return new InquiryListResponse(
                inquiry.getId(),
                inquiry.getTitle(),
                preview,
                inquiry.getStatus(),
                inquiry.getReplyContent(),
                inquiry.getCreatedAt()
        );
    }
}
