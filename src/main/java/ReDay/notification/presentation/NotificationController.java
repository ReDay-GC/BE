package ReDay.notification.presentation;

import ReDay.common.response.CommonResponse;
import ReDay.common.response.ResponseMessage;
import ReDay.notification.application.dto.request.NotificationSettingRequest;
import ReDay.notification.application.dto.response.NotificationResponse;
import ReDay.notification.application.dto.response.NotificationSettingResponse;
import ReDay.notification.application.usecase.GetNotificationSettingUseCase;
import ReDay.notification.application.usecase.GetNotificationsUseCase;
import ReDay.notification.application.usecase.ReadNotificationUseCase;
import ReDay.notification.application.usecase.UpdateNotificationSettingUseCase;
import ReDay.notification.domain.entity.NotificationCategory;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Notification", description = "알림 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/notifications")
public class NotificationController {

    private final GetNotificationsUseCase getNotificationsUseCase;
    private final ReadNotificationUseCase readNotificationUseCase;
    private final GetNotificationSettingUseCase getNotificationSettingUseCase;
    private final UpdateNotificationSettingUseCase updateNotificationSettingUseCase;

    @Operation(summary = "알림 목록 조회", description = "사용자의 알림 목록을 최신순으로 조회합니다. category: ALL(전체), SYSTEM(시스템), MY(내 알림)")
    @GetMapping
    public CommonResponse<List<NotificationResponse>> getNotifications(
            @AuthenticationPrincipal Long userId,
            @RequestParam(required = false, defaultValue = "ALL") NotificationCategory category
    ) {
        List<NotificationResponse> response = getNotificationsUseCase.execute(userId, category);

        return CommonResponse.success(ResponseMessage.NOTIFICATION_FETCHED, response);
    }

    @Operation(summary = "알림 읽음 처리", description = "특정 알림을 읽음 처리합니다.")
    @PatchMapping("/{notificationId}/read")
    public CommonResponse<Void> readNotification(
            @PathVariable Long notificationId
    ) {
        readNotificationUseCase.execute(notificationId);

        return CommonResponse.success(ResponseMessage.NOTIFICATION_READ, null);
    }

    @Operation(summary = "알림 설정 조회", description = "사용자의 알림 설정을 조회합니다.")
    @GetMapping("/settings")
    public CommonResponse<NotificationSettingResponse> getNotificationSetting(
            @AuthenticationPrincipal Long userId
    ) {
        NotificationSettingResponse response = getNotificationSettingUseCase.execute(userId);

        return CommonResponse.success(ResponseMessage.NOTIFICATION_SETTING_FETCHED, response);
    }

    @Operation(summary = "알림 설정 변경", description = "사용자의 알림 설정을 변경합니다.")
    @PutMapping("/settings")
    public CommonResponse<NotificationSettingResponse> updateNotificationSetting(
            @AuthenticationPrincipal Long userId,
            @RequestBody NotificationSettingRequest request
    ) {
        NotificationSettingResponse response = updateNotificationSettingUseCase.execute(userId, request);

        return CommonResponse.success(ResponseMessage.NOTIFICATION_SETTING_UPDATED, response);
    }
}
