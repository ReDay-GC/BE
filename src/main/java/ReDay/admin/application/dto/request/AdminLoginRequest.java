package ReDay.admin.application.dto.request;

public record AdminLoginRequest(
        String email,
        String password
) {
}
