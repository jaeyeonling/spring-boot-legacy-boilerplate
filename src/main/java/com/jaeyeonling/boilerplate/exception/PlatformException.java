package com.jaeyeonling.boilerplate.exception;

import com.jaeyeonling.boilerplate.model.Error;
import com.jaeyeonling.boilerplate.model.Header;
import lombok.Builder;
import org.springframework.http.ResponseEntity;

@Builder
public class PlatformException extends RuntimeException {
    @Builder.Default
    private PlatformStatus status = PlatformStatus.INTERNAL_SERVER_ERROR;
    private Header header;

    //
    //
    //

    ResponseEntity<Error> toResponse() {
        return status.toResponse(header);
    }

    @Override
    public String getMessage() {
        return status.toString();
    }
}
