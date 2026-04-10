package ReDay.admin.domain.service;

import ReDay.admin.application.exception.AdminNotFoundException;
import ReDay.admin.domain.entity.Admin;
import ReDay.admin.domain.repository.AdminRepository;
import ReDay.application.exception.BusinessException;
import ReDay.application.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AdminAuthService {

    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;

    public Admin authenticate(String email, String password) {
        Admin admin = adminRepository.findByEmail(email)
                .orElseThrow(AdminNotFoundException::new);

        if (!passwordEncoder.matches(password, admin.getPassword())) {
            throw new BusinessException(ErrorCode.INVALID_CREDENTIALS);
        }

        return admin;
    }
}
