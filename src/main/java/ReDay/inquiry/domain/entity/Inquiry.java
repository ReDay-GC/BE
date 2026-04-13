package ReDay.inquiry.domain.entity;

import ReDay.domain.entity.BaseTimeEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "inquiry")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Inquiry extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false, length = 100)
    private String title;

    @Lob
    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private InquiryStatus status;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String replyContent;

    private LocalDateTime repliedAt;

    @Builder
    private Inquiry(Long userId, String title, String content) {
        this.userId = userId;
        this.title = title;
        this.content = content;
        this.status = InquiryStatus.WAITING;
    }

    public void reply(String content) {
        this.replyContent = content;
        this.repliedAt = LocalDateTime.now();
        this.status = InquiryStatus.ANSWERED;
    }

    public void changeStatus(InquiryStatus newStatus) {
        this.status = newStatus;
    }
}
