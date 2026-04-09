package ReDay.memory.application.usecase;

import ReDay.memory.application.dto.response.MemoryMapPinResponse;
import ReDay.memory.domain.entity.Memory;
import ReDay.memory.domain.service.MemoryGetService;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetMemoryMapUseCase {

    private final MemoryGetService memoryGetService;

    public List<MemoryMapPinResponse> execute() {
        Map<String, Long> locationCountMap = memoryGetService.getMemoriesWithLocation()
                .stream()
                .collect(Collectors.groupingBy(Memory::getLocation, Collectors.counting()));

        return locationCountMap.entrySet()
                .stream()
                .map(entry -> new MemoryMapPinResponse(entry.getKey(), entry.getValue().intValue()))
                .toList();
    }
}
