package com.jaeyeonling.boilerplate.security.social;

import com.jaeyeonling.boilerplate.exception.PlatformException;
import com.jaeyeonling.boilerplate.exception.PlatformStatus;
import com.jaeyeonling.boilerplate.model.Header;
import com.jaeyeonling.boilerplate.properties.SecurityProperties;
import com.jaeyeonling.boilerplate.type.AuthProvider;
import com.jaeyeonling.boilerplate.utils.RestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;

public abstract class SocialFetchService<T extends SocialUserInfo> {

    private final SecurityProperties securityProperties;
    private final RestTemplate restTemplate;

    //
    //
    //

    public SocialFetchService(
            final SecurityProperties securityProperties,
            final RestTemplate restTemplate
    ) {
        this.securityProperties = securityProperties;
        this.restTemplate = restTemplate;
    }

    //
    //
    //

    protected abstract AuthProvider getAuthProvider();
    protected abstract Class<T> getSocialUserInfoType();

    public T getSocialUserInfo(final SocialLoginRequest socialLoginRequest) {
        return exchange(socialLoginRequest).getBody();
    }

    //
    //
    //

    private String getEndPoint() {
        return securityProperties.getEndPoint(getAuthProvider())
                .orElseThrow(() -> PlatformException.builder().status(PlatformStatus.BAD_REQUEST).build());
    }

    private ResponseEntity<T> exchange(final SocialLoginRequest socialLoginRequest) {
        final var header = new Header();
        header.setBearerToken(socialLoginRequest.getToken());

        final var entity = new HttpEntity<>(
                null,
                header
        );

        try {
            return restTemplate.exchange(
                    getEndPoint(),
                    HttpMethod.GET,
                    entity,
                    getSocialUserInfoType()
            );
        } catch (final HttpClientErrorException e) {
            throw recovery(e);
        }
    }

    private PlatformException recovery(final HttpClientErrorException e) {
        if (e.getStatusCode() == HttpStatus.UNAUTHORIZED) {
            return PlatformException.builder().status(PlatformStatus.UNAUTHORIZED).build();
        }

        // TODO: more error handling?

        return PlatformException.builder().status(PlatformStatus.INTERNAL_SERVER_ERROR).build();
    }
}
