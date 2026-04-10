package ReDay.inquiry.domain.service;

import ReDay.inquiry.application.exception.InquiryNotFoundException;
import ReDay.inquiry.domain.entity.Inquiry;
import ReDay.inquiry.domain.repository.InquiryRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class InquiryGetService {

    private final InquiryRepository inquiryRepository;

    public List<Inquiry> getInquiryList(Long userId) {
        return inquiryRepository.findAllByUserIdOrderByCreatedAtDesc(userId);
    }

    public Inquiry getInquiry(Long inquiryId, Long userId) {
        return inquiryRepository.findByIdAndUserId(inquiryId, userId)
                .orElseThrow(InquiryNotFoundException::new);
    }
}
