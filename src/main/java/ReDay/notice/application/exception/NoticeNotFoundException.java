package ReDay.notice.application.exception;

import ReDay.application.exception.BusinessException;
import ReDay.common.response.ResponseMessage;

public class NoticeNotFoundException extends BusinessException {

    public NoticeNotFoundException() {
        super(ResponseMessage.NOTICE_NOT_FOUND);
    }
}
