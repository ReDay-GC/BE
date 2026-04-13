package ReDay.analysis.application.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.util.List;

public record MonthlyInsightReceiveRequest(
        @NotNull Long userId,
        @NotNull Integer year,
        @NotNull Integer month,
        String insightText,
        @Valid List<PersonData> topPeople,
        @Valid List<ActivityData> topActivities
) {
    public record PersonData(
            @NotNull String name,
            @NotNull Integer count
    ) {}

    public record ActivityData(
            @NotNull String activityType,
            @NotNull Integer percentage
    ) {}
}
