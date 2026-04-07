package ReDay.memory.application.exception;

import ReDay.application.exception.BusinessException;
import ReDay.common.response.ResponseMessage;

public class MemoryNotFoundException extends BusinessException {

    public MemoryNotFoundException() {
        super(ResponseMessage.MEMORY_NOT_FOUND);
    }
}
