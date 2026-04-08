package ReDay.record.application.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record TextRecordRequest(

        @NotNull(message = "날짜를 선택해주세요.")
        LocalDate recordDate,

        @NotBlank(message = "텍스트 내용을 입력해주세요.")
        String textContent,

        String address,
        Double latitude,
        Double longitude,
        LocalDateTime recordedAt
) {
}
