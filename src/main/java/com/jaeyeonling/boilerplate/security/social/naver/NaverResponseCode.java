package com.jaeyeonling.boilerplate.security.social.naver;

import com.jaeyeonling.boilerplate.exception.PlatformException;
import com.jaeyeonling.boilerplate.exception.PlatformStatus;
import lombok.AllArgsConstructor;

// Note: https://developers.naver.com/docs/login/profile/
@AllArgsConstructor
public enum NaverResponseCode {

    SUCCESS("00"),
    AUTHENTICATION_FAILED("024"),
    AUTHENTICATION_HEADER_NOT_EXISTS("028"),
    FORBIDDEN("403"),
    NOT_FOUND("404"),
    INTERNAL_SERVER_ERROR("500"),

    ;

    private String responseCode;

    //
    //
    //

    public void check() {
        switch (this) {
            case SUCCESS:
                return;

            case AUTHENTICATION_FAILED:
            case AUTHENTICATION_HEADER_NOT_EXISTS:
            case FORBIDDEN:
                throw PlatformException.builder().status(PlatformStatus.UNAUTHORIZED).build();

            case NOT_FOUND:
            case INTERNAL_SERVER_ERROR:
                // ignore
        }

        throw PlatformException.builder().status(PlatformStatus.INTERNAL_SERVER_ERROR).build();
    }
}
