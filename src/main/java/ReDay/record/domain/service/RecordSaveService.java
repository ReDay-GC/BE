package ReDay.record.domain.service;

import ReDay.record.application.dto.request.TextRecordRequest;
import ReDay.record.application.dto.response.RecordSaveResponse;
import ReDay.record.application.mapper.RecordMapper;
import ReDay.record.domain.entity.Record;
import ReDay.record.domain.repository.RecordRepository;
import ReDay.record.infrastructure.S3UploadService;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class RecordSaveService {

    private final RecordRepository recordRepository;
    private final S3UploadService s3UploadService;

    @Transactional
    public RecordSaveResponse saveText(Long userId, TextRecordRequest request) {
        Record record = Record.builder()
                .userId(userId)
                .recordType("TEXT")
                .textContent(request.textContent())
                .recordDate(request.recordDate())
                .recordedAt(request.recordedAt() != null ? request.recordedAt() : LocalDateTime.now())
                .address(request.address())
                .latitude(request.latitude())
                .longitude(request.longitude())
                .createdAt(LocalDateTime.now())
                .build();

        return RecordMapper.toResponse(recordRepository.save(record));
    }

    @Transactional
    public RecordSaveResponse savePhoto(Long userId, MultipartFile photo,
                                        String textContent, String address,
                                        Double latitude, Double longitude,
                                        java.time.LocalDate recordDate,
                                        LocalDateTime recordedAt) {
        s3UploadService.validateImageFile(photo);
        String mediaUrl = s3UploadService.upload(photo, "photos");

        Record record = Record.builder()
                .userId(userId)
                .recordType("PHOTO")
                .textContent(textContent)
                .mediaUrl(mediaUrl)
                .mediaType(photo.getContentType())
                .recordDate(recordDate)
                .recordedAt(recordedAt != null ? recordedAt : LocalDateTime.now())
                .address(address)
                .latitude(latitude)
                .longitude(longitude)
                .createdAt(LocalDateTime.now())
                .build();

        return RecordMapper.toResponse(recordRepository.save(record));
    }

    @Transactional
    public RecordSaveResponse saveVoice(Long userId, MultipartFile audio,
                                        String textContent, String address,
                                        Double latitude, Double longitude,
                                        java.time.LocalDate recordDate,
                                        LocalDateTime recordedAt,
                                        Integer voiceDurationSeconds) {
        s3UploadService.validateAudioFile(audio);
        String mediaUrl = s3UploadService.upload(audio, "voices");

        Record record = Record.builder()
                .userId(userId)
                .recordType("VOICE")
                .textContent(textContent)
                .mediaUrl(mediaUrl)
                .mediaType(audio.getContentType())
                .voiceDurationSeconds(voiceDurationSeconds)
                .recordDate(recordDate)
                .recordedAt(recordedAt != null ? recordedAt : LocalDateTime.now())
                .address(address)
                .latitude(latitude)
                .longitude(longitude)
                .createdAt(LocalDateTime.now())
                .build();

        return RecordMapper.toResponse(recordRepository.save(record));
    }
}
