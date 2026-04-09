package ReDay.record.application.exception;

import ReDay.application.exception.BusinessException;
import ReDay.common.response.ResponseMessage;

public class RecordNotFoundException extends BusinessException {

    public RecordNotFoundException() {
        super(ResponseMessage.RECORD_NOT_FOUND);
    }
}
