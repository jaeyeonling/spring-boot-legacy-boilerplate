package com.jaeyeonling.oauth2.security.verifier;

import com.jaeyeonling.oauth2.entity.Authentication;

public interface Verifier {

    boolean verify(
            final Authentication authentication,
            final String inputValue
    );
}
