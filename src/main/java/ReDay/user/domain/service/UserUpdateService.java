package ReDay.user.domain.service;

import ReDay.user.domain.entity.User;
import ReDay.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserUpdateService {

    private final UserRepository userRepository;
    private final UserGetService userGetService;

    @Transactional
    public User updateName(Long userId, String name) {
        User user = userGetService.getUser(userId);
        user.updateName(name);
        return user;
    }
}
