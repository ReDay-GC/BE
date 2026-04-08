package ReDay.memory.application.exception;

import ReDay.application.exception.BusinessException;
import ReDay.common.response.ResponseMessage;

public class MemoryAnalysisFailedException extends BusinessException {

    public MemoryAnalysisFailedException() {
        super(ResponseMessage.MEMORY_ANALYSIS_FAILED);
    }
}
