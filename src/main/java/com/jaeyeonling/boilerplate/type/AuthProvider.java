package com.jaeyeonling.boilerplate.type;

import com.jaeyeonling.boilerplate.entity.Authentication;
import com.jaeyeonling.boilerplate.security.verifier.VerifierProvider;
import com.jaeyeonling.boilerplate.utils.BeanUtils;

public enum AuthProvider {

    SERVER,
    KAKAO,

    // TODO: Google, Github, Facebook, ...

    ;

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
