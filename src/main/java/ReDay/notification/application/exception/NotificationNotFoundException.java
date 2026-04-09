package ReDay.notification.application.exception;

import ReDay.application.exception.BusinessException;
import ReDay.common.response.ResponseMessage;

public class NotificationNotFoundException extends BusinessException {

    public NotificationNotFoundException() {
        super(ResponseMessage.NOTIFICATION_NOT_FOUND);
    }
}
