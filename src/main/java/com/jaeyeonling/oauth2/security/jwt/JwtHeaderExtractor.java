package com.jaeyeonling.oauth2.security.jwt;

import com.jaeyeonling.oauth2.exception.PlatformException;
import com.jaeyeonling.oauth2.exception.PlatformStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
class JwtHeaderExtractor {
    private final String prefix = "Bearer ";

    //
    //
    //

    String extract(final String tokenValue) {
        if (isInvalid(tokenValue)) {
            throw PlatformException.builder().status(PlatformStatus.INVALID_ACCESS_TOKEN).build();
        }

        return tokenValue.substring(prefix.length());
    }

    //
    //
    //

    private boolean isInvalid(final String tokenValue) {
        return StringUtils.isEmpty(tokenValue) || tokenValue.length() < prefix.length();
    }
}
