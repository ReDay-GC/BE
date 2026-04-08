package ReDay.common.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommonResponse<T> {

    private final boolean success;
    private final int code;
    private final String message;
    private final T data;

    public static <T> CommonResponse<T> success(T data) {
        return new CommonResponse<>(
                true,
                ResponseMessage.SUCCESS.getCode(),
                ResponseMessage.SUCCESS.getMessage(),
                data
        );
    }

    public static <T> CommonResponse<T> success(ResponseMessage responseMessage, T data) {
        return new CommonResponse<>(
                true,
                responseMessage.getCode(),
                responseMessage.getMessage(),
                data
        );
    }

    public static CommonResponse<Void> success() {
        return new CommonResponse<>(
                true,
                ResponseMessage.SUCCESS.getCode(),
                ResponseMessage.SUCCESS.getMessage(),
                null
        );
    }

    public static CommonResponse<Void> fail(int code, String message) {
        return new CommonResponse<>(false, code, message, null);
    }
}
