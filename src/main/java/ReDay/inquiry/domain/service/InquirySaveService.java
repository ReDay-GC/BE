package ReDay.inquiry.domain.service;

import ReDay.inquiry.domain.entity.Inquiry;
import ReDay.inquiry.domain.repository.InquiryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class InquirySaveService {

    private final InquiryRepository inquiryRepository;

    public Inquiry save(Inquiry inquiry) {
        return inquiryRepository.save(inquiry);
    }
}
