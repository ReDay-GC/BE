package ReDay.memory.application.dto.response;

public record MemoryRecordResponse(
        Long recordId,
        String recordType,
        String contentUrl,
        String textContent,
        Integer voiceDurationSeconds
) {
}
