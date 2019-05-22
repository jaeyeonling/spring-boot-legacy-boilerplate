package com.jaeyeonling.oauth2.exception;

import com.jaeyeonling.oauth2.model.Error;
import com.jaeyeonling.oauth2.model.Header;
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
