package ReDay.admin.application.usecase;

import ReDay.admin.application.dto.response.AdminInquiryListResponse;
import ReDay.inquiry.domain.entity.Inquiry;
import ReDay.inquiry.domain.entity.InquiryStatus;
import ReDay.inquiry.domain.repository.InquiryRepository;
import ReDay.user.domain.entity.User;
import ReDay.user.domain.repository.UserRepository;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetAdminInquiryListUseCase {

    private final InquiryRepository inquiryRepository;
    private final UserRepository userRepository;

    public List<AdminInquiryListResponse> execute(InquiryStatus status, String keyword) {
        List<Inquiry> inquiries = fetchInquiries(status, keyword);

        Set<Long> userIds = inquiries.stream()
                .map(Inquiry::getUserId)
                .collect(Collectors.toSet());

        Map<Long, String> userNames = userRepository.findAllById(userIds).stream()
                .collect(Collectors.toMap(User::getId, User::getName));

        return inquiries.stream()
                .map(i -> new AdminInquiryListResponse(
                        i.getId(),
                        i.getUserId(),
                        userNames.getOrDefault(i.getUserId(), "알 수 없음"),
                        i.getTitle(),
                        i.getStatus(),
                        i.getCreatedAt()
                ))
                .toList();
    }

    private List<Inquiry> fetchInquiries(InquiryStatus status, String keyword) {
        boolean hasStatus = status != null;
        boolean hasKeyword = keyword != null && !keyword.isBlank();

        if (hasStatus && hasKeyword) {
            return inquiryRepository.searchByKeywordAndStatus(keyword, status);
        }
        if (hasStatus) {
            return inquiryRepository.findAllByStatusOrderByCreatedAtDesc(status);
        }
        if (hasKeyword) {
            return inquiryRepository.searchByKeyword(keyword);
        }
        return inquiryRepository.findAllByOrderByCreatedAtDesc();
    }
}
