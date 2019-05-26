package com.jaeyeonling.boilerplate.type;

import com.jaeyeonling.boilerplate.entity.Authentication;
import com.jaeyeonling.boilerplate.security.verifier.VerifierProvider;
import com.jaeyeonling.boilerplate.utils.BeanUtils;

import java.util.Optional;

public enum AuthProvider {

    SERVER,
    KAKAO,
    GITHUB,

    // TODO: Google, Github, Facebook, ...

    ;

    public static Optional<AuthProvider> of(final String name) {
        try {
            return Optional.of(valueOf(name.toUpperCase()));
        } catch (final Exception ignore) {
            return Optional.empty();
        }
    }

    //
    //
    //

    public boolean verify(
            final Authentication authentication,
            final String inputPassword
    ) {
        return BeanUtils.getBean(VerifierProvider.class)
                .getVerifier(this)
                .verify(
                        authentication,
                        inputPassword
                );
    }
}