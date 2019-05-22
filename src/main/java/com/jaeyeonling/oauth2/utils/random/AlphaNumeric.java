package com.jaeyeonling.oauth2.utils.random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

@Component
class AlphaNumeric {
    private final Random random = ThreadLocalRandom.current();
    private final String value;

    //
    //
    //

    @Autowired
    public AlphaNumeric(
            final Alphabet alphabet,
            final Digits digits
    ) {
        value = alphabet.getUpperCase() + alphabet.getLowerCase() + digits.getStringDigits();
    }

    //
    //
    //

    char randomChar() {
        return value.charAt(random.nextInt(length()));
    }

    private int length() {
        return value.length();
    }
}
