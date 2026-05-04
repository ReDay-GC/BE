package ReDay.memory.domain.service;

import ReDay.memory.domain.entity.Memory;
import ReDay.memory.domain.repository.MemoryRepository;
import ReDay.memory.application.exception.MemoryNotFoundException;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemoryGetService {

    private final MemoryRepository memoryRepository;

    public Memory getMemory(Long memoryId) {
        return memoryRepository.findById(memoryId)
                .orElseThrow(MemoryNotFoundException::new);
    }

    public List<Memory> getMemoryList(Long userId) {
        return memoryRepository.findAllByUserIdOrderByMemoryDateDesc(userId);
    }

    public List<Memory> searchMemoryByKeyword(Long userId, String keyword) {
        return memoryRepository.searchByUserIdAndKeyword(userId, keyword);
    }

    public List<Memory> getMemoriesByDate(Long userId, LocalDate date) {
        return memoryRepository.findAllByUserIdAndMemoryDate(userId, date);
    }

    public List<Memory> getMemoriesWithLocation(Long userId) {
        return memoryRepository.findAllByUserIdAndLocationIsNotNull(userId);
    }

    public List<Memory> getMemoriesByLocation(Long userId, String location) {
        return memoryRepository.findAllByUserIdAndLocation(userId, location);
    }

    public List<LocalDate> getMemoryDatesByYearMonth(Long userId, int year, int month) {
        YearMonth yearMonth = YearMonth.of(year, month);
        LocalDate startDate = yearMonth.atDay(1);
        LocalDate endDate = yearMonth.atEndOfMonth();

        return memoryRepository.findAllByUserIdAndMemoryDateBetween(userId, startDate, endDate)
                .stream()
                .map(Memory::getMemoryDate)
                .distinct()
                .sorted()
                .toList();
    }
}
