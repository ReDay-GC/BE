package ReDay.admin.application.dto.response;

public record AdminUserStatsResponse(
        long totalUsers,
        long newUsersToday
) {
}
