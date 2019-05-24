package com.jaeyeonling.oauth2.security.social;

import com.jaeyeonling.oauth2.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class SocialLoginRequestMatcher implements RequestMatcher {

    private final AntPathRequestMatcher antPathRequestMatcher;

    //
    //
    //

    @Autowired
    public SocialLoginRequestMatcher(final SecurityProperties securityProperties) {
        antPathRequestMatcher = new AntPathRequestMatcher(securityProperties.getSocialLoginEndPoint());
    }

    //
    //
    //

    @Override
    public boolean matches(final HttpServletRequest request) {
        return antPathRequestMatcher.matches(request);
    }
}
