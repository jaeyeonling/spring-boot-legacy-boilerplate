package com.jaeyeonling.oauth2.security.login;

import com.jaeyeonling.oauth2.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class LoginRequestMatcher implements RequestMatcher {
    private final AntPathRequestMatcher antPathRequestMatcher;

    //
    //
    //

    @Autowired
    private LoginRequestMatcher(final SecurityProperties securityProperties) {
        antPathRequestMatcher = new AntPathRequestMatcher(securityProperties.getLoginEndPoint());
    }

    //
    //
    //

    @Override
    public boolean matches(final HttpServletRequest request) {
        return antPathRequestMatcher.matches(request);
    }
}
