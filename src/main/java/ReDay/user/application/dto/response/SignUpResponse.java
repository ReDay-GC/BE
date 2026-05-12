package ReDay.user.application.dto.response;

public record SignUpResponse(
        Long userId,
        String email,
        String name,
        String accessToken
) {
}
