package com.jaeyeonling.boilerplate.security.social;

import com.jaeyeonling.boilerplate.repository.AuthenticationRepository;
import com.jaeyeonling.boilerplate.security.SecurityUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class SocialLoginAuthenticationProvider implements AuthenticationProvider {

    private final AuthenticationRepository authenticationRepository;
    private final SocialFetchServiceProvider socialFetchServiceProvider;

    //
    //
    //

    @Autowired
    public SocialLoginAuthenticationProvider(
            final AuthenticationRepository authenticationRepository,
            final SocialFetchServiceProvider socialFetchServiceProvider
    ) {
        this.authenticationRepository = authenticationRepository;
        this.socialFetchServiceProvider = socialFetchServiceProvider;
    }

    //
    //
    //

    @Override
    public Authentication authenticate(final Authentication input) throws AuthenticationException {
        final var socialLoginAuthenticationToken = (SocialLoginAuthenticationToken) input;

        final var authProvider = socialLoginAuthenticationToken.getAuthProvider();
        final var socialLoginRequest = socialLoginAuthenticationToken.getSocialLoginRequest();

        final var socialUserInfo = socialFetchServiceProvider.getSocialFetchService(authProvider)
                .getSocialUserInfo(socialLoginRequest);

        final var authentication = authenticationRepository.findByUserIdAndAuthProvider(
                socialUserInfo.getUserId(),
                authProvider
        ).orElseGet(
                () -> authenticationRepository.save(
                        com.jaeyeonling.boilerplate.entity.Authentication.of(socialUserInfo)
                )
        );

        socialLoginAuthenticationToken.setDetails(new SecurityUser(authentication));

        return socialLoginAuthenticationToken;
    }

    @Override
    public boolean supports(final Class<?> clazz) {
        return SocialLoginAuthenticationToken.class.isAssignableFrom(clazz);
    }
}
