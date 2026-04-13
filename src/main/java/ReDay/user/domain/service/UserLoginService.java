package ReDay.user.domain.service;

import ReDay.application.exception.BusinessException;
import ReDay.application.exception.ErrorCode;
import ReDay.config.jwt.JwtProvider;
import ReDay.user.application.dto.request.LoginRequest;
import ReDay.user.application.dto.response.LoginResponse;
import ReDay.user.domain.entity.User;
import ReDay.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserLoginService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    @Transactional(readOnly = true)
    public LoginResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.email())
                .orElseThrow(() -> new BusinessException(ErrorCode.INVALID_CREDENTIALS));

        if (!passwordEncoder.matches(request.password(), user.getPassword())) {
            throw new BusinessException(ErrorCode.INVALID_CREDENTIALS);
        }

        String token = jwtProvider.generateToken(user.getId());
        return new LoginResponse(user.getId(), token);
    }
}
