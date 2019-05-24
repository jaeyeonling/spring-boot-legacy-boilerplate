package com.jaeyeonling.oauth2.model;

import org.springframework.http.HttpHeaders;

public class Header extends HttpHeaders {

    public void setBearerToken(final String token) {
        add("Authorization", "Bearer " + token);
    }
}
