package ReDay.application.exception;

import ReDay.common.response.ResponseMessage;
import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {

    private final int code;
    private final String message;

    public BusinessException(ResponseMessage responseMessage) {
        super(responseMessage.getMessage());
        this.code = responseMessage.getCode();
        this.message = responseMessage.getMessage();
    }
}
