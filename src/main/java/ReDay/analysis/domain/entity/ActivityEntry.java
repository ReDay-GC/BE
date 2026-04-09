package ReDay.analysis.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ActivityEntry {

    @Column(name = "activity_type", length = 100)
    private String activityType;

    @Column(name = "activity_percentage")
    private int percentage;
}
