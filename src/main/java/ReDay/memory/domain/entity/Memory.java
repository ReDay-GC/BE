package ReDay.memory.domain.entity;

import ReDay.domain.entity.BaseTimeEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "memory")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Memory extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(nullable = false, length = 255)
    private String summary;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false, columnDefinition = "DATE")
    private LocalDate memoryDate;

    @Column(length = 50)
    private String emotion;

    @Column(length = 255)
    private String thumbnailUrl;

    @Column(length = 255)
    private String location;

    @Column(nullable = false)
    private boolean archived;

    @Builder
    private Memory(
            Long userId,
            String title,
            String summary,
            String description,
            LocalDate memoryDate,
            String emotion,
            String thumbnailUrl,
            String location,
            boolean archived
    ) {
        this.userId = userId;
        this.title = title;
        this.summary = summary;
        this.description = description;
        this.memoryDate = memoryDate;
        this.emotion = emotion;
        this.thumbnailUrl = thumbnailUrl;
        this.location = location;
        this.archived = archived;
    }

    public void update(
            String title,
            String summary,
            String description,
            LocalDate memoryDate,
            String emotion,
            String thumbnailUrl,
            String location
    ) {
        this.title = title;
        this.summary = summary;
        this.description = description;
        this.memoryDate = memoryDate;
        this.emotion = emotion;
        this.thumbnailUrl = thumbnailUrl;
        this.location = location;
    }

    public void archive() {
        this.archived = true;
    }

    public void restore() {
        this.archived = false;
    }
}
