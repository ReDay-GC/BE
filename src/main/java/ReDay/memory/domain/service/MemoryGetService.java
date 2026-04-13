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

    public List<Memory> getMemoryList() {
        return memoryRepository.findAllByOrderByMemoryDateDesc();
    }

    public List<Memory> searchMemoryByKeyword(String keyword) {
        return memoryRepository.searchByKeyword(keyword);
    }

    public List<Memory> getMemoriesByDate(LocalDate date) {
        return memoryRepository.findAllByMemoryDate(date);
    }

    public List<Memory> getMemoriesWithLocation() {
        return memoryRepository.findAllByLocationIsNotNull();
    }

    public List<Memory> getMemoriesByLocation(String location) {
        return memoryRepository.findAllByLocation(location);
    }

    public List<LocalDate> getMemoryDatesByYearMonth(int year, int month) {
        YearMonth yearMonth = YearMonth.of(year, month);
        LocalDate startDate = yearMonth.atDay(1);
        LocalDate endDate = yearMonth.atEndOfMonth();

        return memoryRepository.findAllByMemoryDateBetween(startDate, endDate)
                .stream()
                .map(Memory::getMemoryDate)
                .distinct()
                .sorted()
                .toList();
    }
}
