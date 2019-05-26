package com.jaeyeonling.boilerplate.properties;

import com.jaeyeonling.boilerplate.type.AuthProvider;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;
import java.util.Optional;

@Setter
@Getter
@Configuration
@ConfigurationProperties(prefix = "jaeyeonling.boilerplate.security")
public class SecurityProperties {

    private String loginEndPoint;
    private String socialLoginEndPoint;
    private int passwordEncodeStrength;
    private Map<AuthProvider, String> authProviderEndPoints;

    //
    //
    //

    public Optional<String> getEndPoint(final AuthProvider authProvider) {
        return Optional.ofNullable(authProviderEndPoints.get(authProvider));
    }
}
