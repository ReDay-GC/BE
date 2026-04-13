package ReDay.memory.application.usecase;

import ReDay.memory.application.dto.response.MemoryMapPinResponse;
import ReDay.memory.domain.entity.Memory;
import ReDay.memory.domain.entity.MemoryRecordMapping;
import ReDay.memory.domain.repository.MemoryRecordMappingRepository;
import ReDay.memory.domain.service.MemoryGetService;
import ReDay.record.domain.entity.Record;
import ReDay.record.domain.repository.RecordRepository;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetMemoryMapUseCase {

    private final MemoryGetService memoryGetService;
    private final MemoryRecordMappingRepository memoryRecordMappingRepository;
    private final RecordRepository recordRepository;

    public List<MemoryMapPinResponse> execute() {
        Map<String, List<Memory>> locationMemoryMap = memoryGetService.getMemoriesWithLocation()
                .stream()
                .collect(Collectors.groupingBy(Memory::getLocation));

        return locationMemoryMap.entrySet().stream()
                .map(entry -> {
                    String location = entry.getKey();
                    List<Memory> memories = entry.getValue();

                    Double latitude = null;
                    Double longitude = null;

                    for (Memory memory : memories) {
                        List<Long> recordIds = memoryRecordMappingRepository.findAllByMemory(memory)
                                .stream()
                                .map(MemoryRecordMapping::getRecordId)
                                .toList();

                        if (!recordIds.isEmpty()) {
                            List<Record> records = recordRepository.findWithLocationByIds(recordIds);
                            if (!records.isEmpty()) {
                                latitude = records.get(0).getLatitude();
                                longitude = records.get(0).getLongitude();
                                break;
                            }
                        }
                    }

                    return new MemoryMapPinResponse(location, memories.size(), latitude, longitude);
                })
                .toList();
    }
}
