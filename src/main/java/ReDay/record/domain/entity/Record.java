package ReDay.record.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Builder
@Table(name = "record")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Record {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false, length = 20)
    private String recordType;

    @Column(columnDefinition = "TEXT")
    private String textContent;

    @Column(length = 500)
    private String mediaUrl;

    @Column(length = 20)
    private String mediaType;

    @Column
    private Integer voiceDurationSeconds;

    @Column(nullable = false)
    private LocalDate recordDate;

    @Column(nullable = false)
    private LocalDateTime recordedAt;

    @Column
    private Double latitude;

    @Column
    private Double longitude;

    @Column(length = 255)
    private String address;

    @Column(nullable = false)
    private LocalDateTime createdAt;
}
