package ReDay.application.exception;

import ReDay.common.response.ResponseMessage;
import lombok.Getter;
import ReDay.application.exception.ErrorCode;

@Getter
public class BusinessException extends RuntimeException {

    private final int code;
    private final String message;

    public BusinessException(ResponseMessage responseMessage) {
        super(responseMessage.getMessage());
        this.code = responseMessage.getCode();
        this.message = responseMessage.getMessage();
    }

    public BusinessException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.code = errorCode.getStatus().value();
        this.message = errorCode.getMessage();
    }
}
