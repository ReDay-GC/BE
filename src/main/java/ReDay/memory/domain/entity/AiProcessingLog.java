package ReDay.memory.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "ai_processing_log")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AiProcessingLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long memoryId;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false, length = 20)
    private String status;

    @Column(nullable = false)
    private Long responseTimeMs;

    @Column(nullable = false)
    private LocalDateTime processedAt;

    @Builder
    private AiProcessingLog(Long memoryId, Long userId, String status, Long responseTimeMs, LocalDateTime processedAt) {
        this.memoryId = memoryId;
        this.userId = userId;
        this.status = status;
        this.responseTimeMs = responseTimeMs;
        this.processedAt = processedAt;
    }
}
