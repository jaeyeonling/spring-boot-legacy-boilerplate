package com.jaeyeonling.oauth2.type;

import com.jaeyeonling.oauth2.entity.Authentication;
import com.jaeyeonling.oauth2.security.PasswordEncoder;
import com.jaeyeonling.oauth2.security.verifier.VerifierProvider;
import com.jaeyeonling.oauth2.utils.BeanUtils;

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
