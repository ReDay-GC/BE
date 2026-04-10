package ReDay.admin.application.dto.response;

public record AdminLoginResponse(
        Long adminId,
        String name,
        String email,
        String token
) {
}
