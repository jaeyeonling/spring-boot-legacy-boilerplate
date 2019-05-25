package com.jaeyeonling.boilerplate.security;

import com.jaeyeonling.boilerplate.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordEncoder extends BCryptPasswordEncoder {

    @Autowired
    public PasswordEncoder(final SecurityProperties securityProperties) {
        super(securityProperties.getPasswordEncodeStrength());
    }
}
