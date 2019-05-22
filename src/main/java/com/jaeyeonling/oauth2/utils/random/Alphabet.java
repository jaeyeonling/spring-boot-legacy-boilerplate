package com.jaeyeonling.oauth2.utils.random;

import lombok.AccessLevel;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Getter(AccessLevel.PACKAGE)
@Component
class Alphabet {
    private final String upperCase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private final String lowerCase = upperCase.toLowerCase();
}
