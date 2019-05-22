package com.jaeyeonling.oauth2.exception;

import com.jaeyeonling.oauth2.model.Error;
import com.jaeyeonling.oauth2.model.Header;
import com.jaeyeonling.oauth2.model.Response;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;

@AllArgsConstructor
public enum PlatformStatus {
    // Global
    BAD_REQUEST(-101, HttpStatus.BAD_REQUEST),
    NOT_FOUND(-102, HttpStatus.NOT_FOUND),
    INTERNAL_SERVER_ERROR(-103, HttpStatus.INTERNAL_SERVER_ERROR),
    UNAUTHORIZED(-104, HttpStatus.UNAUTHORIZED),
    ACCESS_TOKEN_EXPIRED(-105, HttpStatus.UNAUTHORIZED),
    INVALID_ACCESS_TOKEN(-106, HttpStatus.UNAUTHORIZED),
    ACCESS_DENIED(-107, HttpStatus.FORBIDDEN),

    // Account
    ALREADY_EXISTS_USER(-201, HttpStatus.BAD_REQUEST),

    // Other

    ;

    //
    //
    //

    private final int code;

    @Getter
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
