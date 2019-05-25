package com.jaeyeonling.boilerplate.security.verifier;

import com.jaeyeonling.boilerplate.entity.Authentication;

public interface Verifier {

    boolean verify(
            final Authentication authentication,
            final String inputValue
    );
}
