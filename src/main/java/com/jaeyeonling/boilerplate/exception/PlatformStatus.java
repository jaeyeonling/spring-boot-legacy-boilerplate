package com.jaeyeonling.boilerplate.exception;

import com.jaeyeonling.boilerplate.model.Error;
import com.jaeyeonling.boilerplate.model.Header;
import com.jaeyeonling.boilerplate.model.Response;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;

@AllArgsConstructor
public enum PlatformStatus {
    // Global
    BAD_REQUEST(-101, HttpStatus.BAD_REQUEST),
    NOT_FOUND(-102, HttpStatus.NOT_FOUND),
    INTERNAL_SERVER_ERROR(-103, HttpStatus.INTERNAL_SERVER_ERROR),

    // Other

    ;

    //
    //
    //

    private final int code;
    private final HttpStatus httpStatus;

    public ResponseEntity<Error> toResponse() {
        return toResponse(null);
    }

    public ResponseEntity<Error> toResponse(@Nullable final Header header) {
        return Response.status(httpStatus).headers(header).body(toError());
    }

    //
    //
    //

    private Error toError() {
        return Error.builder().code(code).message(name()).build();
    }
}
