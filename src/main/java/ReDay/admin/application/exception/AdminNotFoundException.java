package ReDay.admin.application.exception;

import ReDay.application.exception.BusinessException;
import ReDay.application.exception.ErrorCode;

public class AdminNotFoundException extends BusinessException {

    public AdminNotFoundException() {
        super(ErrorCode.UNAUTHORIZED);
    }
}
