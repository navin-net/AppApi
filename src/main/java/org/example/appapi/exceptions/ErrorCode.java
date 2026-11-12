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