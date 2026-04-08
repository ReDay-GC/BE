package ReDay.user.domain.service;

import ReDay.user.domain.entity.BlacklistedToken;
import ReDay.user.domain.repository.BlacklistedTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserLogoutService {

    private final BlacklistedTokenRepository blacklistedTokenRepository;

    @Transactional
    public void logout(String token) {
        blacklistedTokenRepository.save(BlacklistedToken.builder()
                .token(token)
                .build());
    }
}
