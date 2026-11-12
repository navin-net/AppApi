package org.example.appapi.exceptions;

import lombok.Getter;

@Getter
public class WebException extends RuntimeException {

    private final ErrorCode errorCode;
    private final String detail;

    public WebException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
        this.detail = null;

    }

    // Adds detail to the English message, e.g. "Category not found: 5"
    public WebException(ErrorCode errorCode, String detail) {
        super(errorCode.getMessage() + ": " + detail);
        this.errorCode = errorCode;
        this.detail = detail;

    }

    public WebException(ErrorCode errorCode, Throwable cause) {
        super(errorCode.getMessage(), cause);
        this.errorCode = errorCode;
        this.detail = null;
    }
}