package ReDay.notification.domain.entity;

import ReDay.domain.entity.BaseTimeEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "notification_setting")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class NotificationSetting extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private Long userId;

    @Column(nullable = false)
    private boolean pushEnabled;

    @Column(nullable = false)
    private boolean dailyRecordEnabled;

    @Column(nullable = false)
    private boolean aiGenerationEnabled;

    @Builder
    private NotificationSetting(Long userId) {
        this.userId = userId;
        this.pushEnabled = true;
        this.dailyRecordEnabled = true;
        this.aiGenerationEnabled = true;
    }

    public void update(boolean pushEnabled, boolean dailyRecordEnabled, boolean aiGenerationEnabled) {
        this.pushEnabled = pushEnabled;
        this.dailyRecordEnabled = dailyRecordEnabled;
        this.aiGenerationEnabled = aiGenerationEnabled;
    }
}
