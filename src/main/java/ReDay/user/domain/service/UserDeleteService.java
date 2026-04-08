package ReDay.user.domain.service;

import ReDay.application.exception.BusinessException;
import ReDay.application.exception.ErrorCode;
import ReDay.user.domain.entity.BlacklistedToken;
import ReDay.user.domain.repository.BlacklistedTokenRepository;
import ReDay.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserDeleteService {

    private final UserRepository userRepository;
    private final BlacklistedTokenRepository blacklistedTokenRepository;

    @Transactional
    public void delete(Long userId, String token) {
        if (!userRepository.existsById(userId)) {
            throw new BusinessException(ErrorCode.USER_NOT_FOUND);
        }

        userRepository.deleteById(userId);

        blacklistedTokenRepository.save(BlacklistedToken.builder()
                .token(token)
                .build());
    }
}
