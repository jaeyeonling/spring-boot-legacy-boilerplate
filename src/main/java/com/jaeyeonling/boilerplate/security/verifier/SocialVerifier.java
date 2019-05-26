package com.jaeyeonling.boilerplate.security.verifier;

import com.jaeyeonling.boilerplate.entity.Authentication;
import com.jaeyeonling.boilerplate.security.social.SocialFetchService;
import com.jaeyeonling.boilerplate.security.social.SocialLoginRequest;

public abstract class SocialVerifier implements Verifier {

    private final SocialFetchService socialFetchService;

    //
    //
    //

    public SocialVerifier(final SocialFetchService socialFetchService) {
        this.socialFetchService = socialFetchService;
    }

    //
    //
    //

    @Override
    public boolean verify(
            final Authentication authentication,
            final String token
    ) {
        final var socialLoginRequest = SocialLoginRequest.token(token);

        final var userId = socialFetchService.getSocialUserInfo(socialLoginRequest).getUserId();

        return authentication.getUserId().equals(userId);
    }
}
