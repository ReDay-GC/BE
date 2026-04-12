package ReDay.record.presentation;

import ReDay.common.response.CommonResponse;
import ReDay.common.response.ResponseMessage;
import ReDay.record.application.dto.request.TextRecordRequest;
import ReDay.record.application.dto.response.RecordListItemResponse;
import ReDay.record.application.dto.response.RecordSaveResponse;
import ReDay.record.application.usecase.DeleteRecordUseCase;
import ReDay.record.application.usecase.GetRecordDatesByMonthUseCase;
import ReDay.record.application.usecase.GetRecordsByDateUseCase;
import ReDay.record.application.usecase.SavePhotoRecordUseCase;
import ReDay.record.application.usecase.SaveTextRecordUseCase;
import ReDay.record.application.usecase.SaveVoiceRecordUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    private final DeleteRecordUseCase deleteRecordUseCase;
    private final GetRecordDatesByMonthUseCase getRecordDatesByMonthUseCase;
    private final GetRecordsByDateUseCase getRecordsByDateUseCase;
    private final SaveTextRecordUseCase saveTextRecordUseCase;
    private final SavePhotoRecordUseCase savePhotoRecordUseCase;
    private final SaveVoiceRecordUseCase saveVoiceRecordUseCase;

    @Operation(summary = "월별 기록 있는 날짜 목록 조회")
    @GetMapping("/dates")
    public CommonResponse<Map<String, List<LocalDate>>> getRecordDatesByMonth(
            @AuthenticationPrincipal Long userId,
            @RequestParam int year,
            @RequestParam int month) {
        return CommonResponse.success(ResponseMessage.RECORD_FETCHED,
                Map.of("dates", getRecordDatesByMonthUseCase.execute(userId, year, month)));
    }

    @Operation(summary = "날짜별 기록 조회")
    @GetMapping
    public CommonResponse<List<RecordListItemResponse>> getRecordsByDate(
            @AuthenticationPrincipal Long userId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return CommonResponse.success(ResponseMessage.RECORD_FETCHED,
                getRecordsByDateUseCase.execute(userId, date));
    }

    @Operation(summary = "기록 삭제")
    @DeleteMapping("/{recordId}")
    public CommonResponse<Void> deleteRecord(@PathVariable Long recordId) {
        deleteRecordUseCase.execute(recordId);

        return CommonResponse.success(ResponseMessage.RECORD_DELETED, null);
    }

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
