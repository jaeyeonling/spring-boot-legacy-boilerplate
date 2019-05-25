package com.jaeyeonling.boilerplate.model;

import org.springframework.http.HttpHeaders;

public class Header extends HttpHeaders {

    public void setBearerToken(final String token) {
        add("Authorization", "Bearer " + token);
    }
}
