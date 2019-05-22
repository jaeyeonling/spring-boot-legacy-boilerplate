package com.jaeyeonling.oauth2.type;

import com.jaeyeonling.oauth2.entity.Authentication;
import com.jaeyeonling.oauth2.security.PasswordEncoder;
import com.jaeyeonling.oauth2.utils.BeanUtils;

public enum AuthProvider {

    SERVER {
        @Override
        public boolean verify(
                final Authentication authentication,
                final String inputPassword
        ) {
            return matches(
                    inputPassword,
                    authentication.getPassword()
            );
        }

        //
        //

        private boolean matches(
                final String rawPassword,
                final String encodedPassword
        ) {
            return BeanUtils.getBean(PasswordEncoder.class).matches(
                    rawPassword,
                    encodedPassword
            );
        }
    }

    // TODO: Google, Github, Facebook, ...

    ;

    public abstract boolean verify(final Authentication authentication, final String inputPassword);
}
