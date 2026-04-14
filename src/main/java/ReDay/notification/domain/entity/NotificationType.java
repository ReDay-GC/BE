package ReDay.notification.domain.entity;

public enum NotificationType {
    AI_GENERATION(NotificationCategory.MY),
    DAILY_RECORD(NotificationCategory.MY),
    NOTICE(NotificationCategory.SYSTEM),
    INQUIRY_ANSWER(NotificationCategory.SYSTEM),
    MAINTENANCE(NotificationCategory.SYSTEM);

    private final NotificationCategory category;

    NotificationType(NotificationCategory category) {
        this.category = category;
    }

    public NotificationCategory getCategory() {
        return category;
    }
}
