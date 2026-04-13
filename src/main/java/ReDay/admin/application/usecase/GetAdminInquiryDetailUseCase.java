package ReDay.admin.application.usecase;

import ReDay.admin.application.dto.response.AdminInquiryDetailResponse;
import ReDay.inquiry.application.exception.InquiryNotFoundException;
import ReDay.inquiry.domain.entity.Inquiry;
import ReDay.inquiry.domain.repository.InquiryRepository;
import ReDay.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetAdminInquiryDetailUseCase {

    private final InquiryRepository inquiryRepository;
    private final UserRepository userRepository;

    public AdminInquiryDetailResponse execute(Long inquiryId) {
        Inquiry inquiry = inquiryRepository.findById(inquiryId)
                .orElseThrow(InquiryNotFoundException::new);

        String userName = userRepository.findById(inquiry.getUserId())
                .map(u -> u.getName())
                .orElse("알 수 없음");

        return new AdminInquiryDetailResponse(
                inquiry.getId(),
                inquiry.getUserId(),
                userName,
                inquiry.getTitle(),
                inquiry.getContent(),
                inquiry.getStatus(),
                inquiry.getReplyContent(),
                inquiry.getRepliedAt(),
                inquiry.getCreatedAt()
        );
    }
}
