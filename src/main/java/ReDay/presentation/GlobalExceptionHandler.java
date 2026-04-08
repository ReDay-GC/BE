package ReDay.presentation;

import ReDay.application.exception.BusinessException;
import ReDay.application.exception.ErrorCode;
import ReDay.common.response.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<CommonResponse<Void>> handleBusinessException(BusinessException e) {
        log.warn("BusinessException: {}", e.getMessage());

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(CommonResponse.fail(e.getCode(), e.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CommonResponse<Void>> handleValidationException(MethodArgumentNotValidException e) {
        String message = e.getBindingResult().getFieldErrors().stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.joining(", "));

        log.warn("ValidationException: {}", message);

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(CommonResponse.fail(HttpStatus.BAD_REQUEST.value(), message));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<CommonResponse<Void>> handleException(Exception e) {
        log.error("UnhandledException: ", e);

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(CommonResponse.fail(
                        HttpStatus.INTERNAL_SERVER_ERROR.value(),
                        ErrorCode.INTERNAL_SERVER_ERROR.getMessage()
                ));
    }
}
