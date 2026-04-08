package ReDay.record.presentation;

import ReDay.common.response.CommonResponse;
import ReDay.common.response.ResponseMessage;
import ReDay.record.application.dto.request.TextRecordRequest;
import ReDay.record.application.dto.response.RecordSaveResponse;
import ReDay.record.application.usecase.SavePhotoRecordUseCase;
import ReDay.record.application.usecase.SaveTextRecordUseCase;
import ReDay.record.application.usecase.SaveVoiceRecordUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Tag(name = "Record", description = "기록 수집 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/records")
public class RecordController {

    private final SaveTextRecordUseCase saveTextRecordUseCase;
    private final SavePhotoRecordUseCase savePhotoRecordUseCase;
    private final SaveVoiceRecordUseCase saveVoiceRecordUseCase;

    @Operation(summary = "텍스트 기록 저장")
    @PostMapping("/text")
    @ResponseStatus(HttpStatus.CREATED)
    public CommonResponse<RecordSaveResponse> saveTextRecord(
            @AuthenticationPrincipal Long userId,
            @Valid @RequestBody TextRecordRequest request) {
        return CommonResponse.success(ResponseMessage.RECORD_CREATED,
                saveTextRecordUseCase.execute(userId, request));
    }

    @Operation(summary = "사진 기록 저장")
    @PostMapping(value = "/photo", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public CommonResponse<RecordSaveResponse> savePhotoRecord(
            @AuthenticationPrincipal Long userId,
            @RequestPart("photo") MultipartFile photo,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate recordDate,
            @RequestParam(required = false) String textContent,
            @RequestParam(required = false) String address,
            @RequestParam(required = false) Double latitude,
            @RequestParam(required = false) Double longitude,
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime recordedAt) {
        return CommonResponse.success(ResponseMessage.RECORD_CREATED,
                savePhotoRecordUseCase.execute(userId, photo, textContent, address,
                        latitude, longitude, recordDate, recordedAt));
    }

    @Operation(summary = "음성 기록 저장")
    @PostMapping(value = "/voice", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public CommonResponse<RecordSaveResponse> saveVoiceRecord(
            @AuthenticationPrincipal Long userId,
            @RequestPart("audio") MultipartFile audio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate recordDate,
            @RequestParam(required = false) String textContent,
            @RequestParam(required = false) String address,
            @RequestParam(required = false) Double latitude,
            @RequestParam(required = false) Double longitude,
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime recordedAt,
            @RequestParam(required = false) Integer voiceDurationSeconds) {
        return CommonResponse.success(ResponseMessage.RECORD_CREATED,
                saveVoiceRecordUseCase.execute(userId, audio, textContent, address,
                        latitude, longitude, recordDate, recordedAt, voiceDurationSeconds));
    }
}
