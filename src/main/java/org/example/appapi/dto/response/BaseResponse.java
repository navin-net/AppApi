package org.example.appapi.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseResponse<T> {

    private String code;
    private String message;
    private String messageKh;
    private T data;

    public static <T> BaseResponse<T> success(String message, String messageKh, T data) {
        return BaseResponse.<T>builder()
                .code("SUCCESS")
                .message(message)
                .messageKh(messageKh)
                .data(data)
                .build();
    }

    // Shortcut when you only have English text for now
    public static <T> BaseResponse<T> success(String message, T data) {
        return success(message, message, data);
    }

    public static <T> BaseResponse<T> error(String code, String message, String messageKh) {
        return BaseResponse.<T>builder()
                .code(code)
                .message(message)
                .messageKh(messageKh)   // fixed
                .build();
    }
}