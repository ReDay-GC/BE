package ReDay.analysis.domain.entity;

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
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "monthly_insight")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MonthlyInsight extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private int year;

    @Column(nullable = false)
    private int month;

    @Column(columnDefinition = "TEXT")
    private String insightText;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "monthly_insight_people", joinColumns = @JoinColumn(name = "insight_id"))
    private List<PersonEntry> topPeople;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "monthly_insight_activities", joinColumns = @JoinColumn(name = "insight_id"))
    private List<ActivityEntry> topActivities;

    @Builder
    private MonthlyInsight(Long userId, int year, int month, String insightText,
                           List<PersonEntry> topPeople, List<ActivityEntry> topActivities) {
        this.userId = userId;
        this.year = year;
        this.month = month;
        this.insightText = insightText;
        this.topPeople = topPeople;
        this.topActivities = topActivities;
    }
}
