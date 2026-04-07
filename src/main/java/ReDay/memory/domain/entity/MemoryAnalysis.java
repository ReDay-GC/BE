package ReDay.memory.domain.entity;

import ReDay.domain.entity.BaseTimeEntity;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "memory_analysis")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemoryAnalysis extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long memoryId;

    @Column(nullable = false, length = 50)
    private String emotionResult;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(
            name = "memory_analysis_keywords",
            joinColumns = @JoinColumn(name = "analysis_id")
    )
    @Column(name = "keyword")
    private List<String> keywords;

    @Column(length = 255)
    private String placeSummary;

    @Column(length = 255)
    private String activitySummary;

    @Column(columnDefinition = "TEXT")
    private String overallSummary;

    @Column(nullable = false)
    private LocalDateTime analyzedAt;

    @Builder
    private MemoryAnalysis(
            Long memoryId,
            String emotionResult,
            List<String> keywords,
            String placeSummary,
            String activitySummary,
            String overallSummary
    ) {
        this.memoryId = memoryId;
        this.emotionResult = emotionResult;
        this.keywords = keywords;
        this.placeSummary = placeSummary;
        this.activitySummary = activitySummary;
        this.overallSummary = overallSummary;
        this.analyzedAt = LocalDateTime.now();
    }
}
