package com.jaeyeonling.boilerplate.security.jwt;

import com.jaeyeonling.boilerplate.properties.JwtProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JwtRequestMatcher implements RequestMatcher {

    private final OrRequestMatcher skipRequestMatcher;
    private final OrRequestMatcher checkRequestMatcher;

    //
    //
    //

    @Autowired
    private JwtRequestMatcher(final JwtProperties jwtProperties) {
        skipRequestMatcher = pathsToOrRequestMatchers(jwtProperties.getSkipPaths());
        checkRequestMatcher = pathsToOrRequestMatchers(jwtProperties.getCheckPaths());
    }

    //
    //
    //

    @Override
    public boolean matches(final HttpServletRequest request) {
        return isNotSkip(request) && isCheck(request);
    }

    //
    //
    //

    private boolean isNotSkip(final HttpServletRequest request) {
        return !skipRequestMatcher.matches(request);
    }

    private boolean isCheck(final HttpServletRequest request) {
        return checkRequestMatcher.matches(request);
    }

    private OrRequestMatcher pathsToOrRequestMatchers(final List<String> paths) {
        return new OrRequestMatcher(pathsToRequestMatchers(paths));
    }

    private List<RequestMatcher> pathsToRequestMatchers(final List<String> pathList) {
        return pathList.stream().map(AntPathRequestMatcher::new).collect(Collectors.toList());
    }
}
