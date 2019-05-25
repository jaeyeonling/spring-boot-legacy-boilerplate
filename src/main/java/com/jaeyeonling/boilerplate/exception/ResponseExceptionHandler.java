package com.jaeyeonling.boilerplate.exception;

import com.auth0.jwt.exceptions.JWTDecodeException;
import com.jaeyeonling.boilerplate.model.Error;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@ControllerAdvice
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(PlatformException.class)
    public ResponseEntity<?> platformException(final PlatformException e) {
        return e.toResponse();
    }

    @ExceptionHandler(JWTDecodeException.class)
    public ResponseEntity<?> jwtDecodeException(final JWTDecodeException e) {
        log.debug("JWT decode exception", e);

        return PlatformStatus.UNAUTHORIZED.toResponse();
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<?> accessDeniedException(final AccessDeniedException e) {
        return PlatformStatus.ACCESS_DENIED.toResponse();
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Error> uncaughtException(final Exception e) {
        log.error("Uncaught exception", e);

        return PlatformStatus.INTERNAL_SERVER_ERROR.toResponse();
    }
}