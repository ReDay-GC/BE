package ReDay.user.application.mapper;

import ReDay.user.application.dto.request.SignUpRequest;
import ReDay.user.application.dto.response.SignUpResponse;
import ReDay.user.domain.entity.User;

public class UserMapper {

    public static User toEntity(SignUpRequest request, String encodedPassword) {
        return User.builder()
                .name(request.name())
                .email(request.email())
                .password(encodedPassword)
                .termsAgreed(request.termsAgreed())
                .privacyAgreed(request.privacyAgreed())
                .build();
    }

    public static SignUpResponse toResponse(User user, String accessToken) {
        return new SignUpResponse(
                user.getId(),
                user.getEmail(),
                user.getName(),
                accessToken
        );
    }
}
