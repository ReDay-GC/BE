package ReDay.user.application.dto.response;

public record LoginResponse(
        Long userId,
        String accessToken
) {
}
