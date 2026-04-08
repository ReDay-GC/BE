package ReDay.record.application.usecase;

import ReDay.record.application.dto.response.RecordSaveResponse;
import ReDay.record.domain.service.RecordSaveService;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
@RequiredArgsConstructor
public class SavePhotoRecordUseCase {

    private final RecordSaveService recordSaveService;

    public RecordSaveResponse execute(Long userId, MultipartFile photo,
                                      String textContent, String address,
                                      Double latitude, Double longitude,
                                      LocalDate recordDate, LocalDateTime recordedAt) {
        return recordSaveService.savePhoto(userId, photo, textContent, address,
                latitude, longitude, recordDate, recordedAt);
    }
}
