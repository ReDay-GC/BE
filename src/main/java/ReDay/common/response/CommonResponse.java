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
    private final String message;
    private final T data;

    public static <T> CommonResponse<T> ok(T data) {
        return new CommonResponse<>(true, ResponseMessage.SUCCESS.getMessage(), data);
    }

    public static <T> CommonResponse<T> ok(ResponseMessage responseMessage, T data) {
        return new CommonResponse<>(true, responseMessage.getMessage(), data);
    }

    public static CommonResponse<Void> ok() {
        return new CommonResponse<>(true, ResponseMessage.SUCCESS.getMessage(), null);
    }

    public static CommonResponse<Void> fail(String message) {
        return new CommonResponse<>(false, message, null);
    }
}
