package org.example.appapi.exceptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    // Generic
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "BAD_REQUEST",
            "Bad request", "សំណើមិនត្រឹមត្រូវ"),
    VALIDATION_FAILED(HttpStatus.BAD_REQUEST, "VALIDATION_FAILED",
            "Validation failed", "ទិន្នន័យមិនត្រឹមត្រូវ"),
    INTERNAL_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "INTERNAL_ERROR",
            "Internal server error", "កំហុសខាងម៉ាស៊ីនមេ"),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED,"ERR-401",
            "Authentication required","តម្រូវឱ្យចូលប្រើប្រព័ន្ធ"),
    FORBIDDEN(HttpStatus.FORBIDDEN, "FORBIDDEN",
            "You do not have permission to perform this action", "អ្នកគ្មានសិទ្ធិអនុវត្តសកម្មភាពនេះទេ"),
    INVALID_CREDENTIALS(HttpStatus.UNAUTHORIZED, "INVALID_CREDENTIALS",
            "Invalid username or password", "ឈ្មោះអ្នកប្រើ ឬពាក្យសម្ងាត់មិនត្រឹមត្រូវ"),


    NOT_FOUND(HttpStatus.NOT_FOUND, "NOT_FOUND",
            "Resource not found", "រកមិនឃើញទិន្នន័យ"),
    METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED, "METHOD_NOT_ALLOWED",
            "Method not allowed", "វិធីសាស្ត្រមិនត្រូវបានអនុញ្ញាត"),

    DUPLICATE_DATA(HttpStatus.CONFLICT, "CONFLICT",
            "Duplicate Data", "ទិន្នន័យស្ទួន");


    private final HttpStatus status;
    private final String code;
    private final String message;
    private final String messageKh;
}