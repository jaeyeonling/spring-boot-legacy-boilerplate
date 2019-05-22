package com.jaeyeonling.oauth2.security.login;

import com.jaeyeonling.oauth2.exception.PlatformException;
import com.jaeyeonling.oauth2.exception.PlatformStatus;
import com.jaeyeonling.oauth2.repository.AuthenticationRepository;
import com.jaeyeonling.oauth2.security.PasswordEncoder;
import com.jaeyeonling.oauth2.security.SecurityUser;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class LoginAuthenticationProvider implements AuthenticationProvider {

    private final AuthenticationRepository authenticationRepository;

    //
    //
    //

    private LoginAuthenticationProvider(final AuthenticationRepository authenticationRepository) {
        this.authenticationRepository = authenticationRepository;
    }

    //
    //
    //

    @Override
    public Authentication authenticate(final Authentication input) throws AuthenticationException {
        final var loginAuthenticationToken = (LoginAuthenticationToken) input;

        final var inputUserId = loginAuthenticationToken.getUserId();
        final var inputPassword = loginAuthenticationToken.getPassword();

        final var authentication = authenticationRepository.findByUserId(inputUserId).orElseThrow(
                () -> PlatformException.builder().status(PlatformStatus.UNAUTHORIZED).build()
        );

        if (!authentication.isVerify(inputPassword)) {
            throw PlatformException.builder().status(PlatformStatus.UNAUTHORIZED).build();
        }

        loginAuthenticationToken.setDetails(new SecurityUser(authentication));

        return loginAuthenticationToken;
    }

    @Override
    public boolean supports(final Class<?> clazz) {
        return LoginAuthenticationToken.class.isAssignableFrom(clazz);
    }
}
