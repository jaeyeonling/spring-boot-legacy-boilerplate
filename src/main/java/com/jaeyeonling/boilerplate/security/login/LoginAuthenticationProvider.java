package com.jaeyeonling.boilerplate.security.login;

import com.jaeyeonling.boilerplate.exception.PlatformException;
import com.jaeyeonling.boilerplate.exception.PlatformStatus;
import com.jaeyeonling.boilerplate.repository.AuthenticationRepository;
import com.jaeyeonling.boilerplate.security.SecurityUser;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    public LoginAuthenticationProvider(final AuthenticationRepository authenticationRepository) {
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
