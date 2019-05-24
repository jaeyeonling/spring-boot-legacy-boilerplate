package com.jaeyeonling.oauth2.security.verifier;

import com.jaeyeonling.oauth2.entity.Authentication;
import com.jaeyeonling.oauth2.security.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ServerVerifier implements Verifier {

    private final PasswordEncoder passwordEncoder;

    //
    //
    //

    @Autowired
    public ServerVerifier(final PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    //
    //
    //

    @Override
    public boolean verify(
            final Authentication authentication,
            final String inputPassword
    ) {
        return passwordEncoder.matches(
                inputPassword,
                authentication.getPassword()
        );
    }
}