package ReDay.inquiry.application.exception;

import ReDay.application.exception.BusinessException;
import ReDay.common.response.ResponseMessage;

public class InquiryNotFoundException extends BusinessException {

    public InquiryNotFoundException() {
        super(ResponseMessage.INQUIRY_NOT_FOUND);
    }
}
