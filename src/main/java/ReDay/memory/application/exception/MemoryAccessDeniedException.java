package ReDay.memory.application.exception;

import ReDay.application.exception.BusinessException;
import ReDay.common.response.ResponseMessage;

public class MemoryAccessDeniedException extends BusinessException {

    public MemoryAccessDeniedException() {
        super(ResponseMessage.MEMORY_ACCESS_DENIED);
    }
}
