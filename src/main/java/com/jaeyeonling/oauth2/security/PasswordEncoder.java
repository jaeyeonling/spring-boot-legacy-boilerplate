package com.jaeyeonling.oauth2.security;

import com.jaeyeonling.oauth2.properties.SecurityProperties;
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
