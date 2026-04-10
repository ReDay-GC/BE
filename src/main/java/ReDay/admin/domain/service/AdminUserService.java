package ReDay.admin.domain.service;

import ReDay.application.exception.BusinessException;
import ReDay.application.exception.ErrorCode;
import ReDay.memory.domain.entity.Memory;
import ReDay.memory.domain.repository.MemoryRecordMappingRepository;
import ReDay.record.domain.repository.RecordRepository;
import ReDay.user.domain.entity.User;
import ReDay.user.domain.repository.UserRepository;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AdminUserService {

    private final UserRepository userRepository;
    private final RecordRepository recordRepository;
    private final MemoryRecordMappingRepository memoryRecordMappingRepository;

    @Transactional(readOnly = true)
    public long getTotalUserCount() {
        return userRepository.count();
    }

    @Transactional(readOnly = true)
    public long getNewUserCountToday() {
        LocalDateTime startOfDay = LocalDate.now().atStartOfDay();
        return userRepository.countByCreatedAtAfter(startOfDay);
    }

    @Transactional(readOnly = true)
    public List<User> getUserList(String search) {
        if (search != null && !search.isBlank()) {
            return userRepository.findAllByNameContainingIgnoreCaseOrderByCreatedAtDesc(search);
        }
        return userRepository.findAll(
                org.springframework.data.domain.Sort.by(
                        org.springframework.data.domain.Sort.Direction.DESC, "createdAt"));
    }

    @Transactional(readOnly = true)
    public long getMemoryCountByUserId(Long userId) {
        return memoryRecordMappingRepository.countMemoriesByUserId(userId);
    }

    @Transactional(readOnly = true)
    public User getUser(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));
    }

    @Transactional(readOnly = true)
    public long getTotalRecordCount(Long userId) {
        return recordRepository.countByUserId(userId);
    }

    @Transactional(readOnly = true)
    public long getRecordCountByType(Long userId, String type) {
        return recordRepository.countByUserIdAndRecordType(userId, type);
    }

    @Transactional(readOnly = true)
    public List<Memory> getMemoriesByUserId(Long userId) {
        return memoryRecordMappingRepository.findMemoriesByUserId(userId);
    }

    @Transactional(readOnly = true)
    public long getRecordCountByMemory(Long memoryId) {
        return memoryRecordMappingRepository.countByMemoryId(memoryId);
    }

    @Transactional
    public User updateUser(Long userId, String name) {
        User user = getUser(userId);
        user.updateName(name);
        return user;
    }

    @Transactional
    public void deleteUser(Long userId) {
        User user = getUser(userId);
        userRepository.delete(user);
    }
}
