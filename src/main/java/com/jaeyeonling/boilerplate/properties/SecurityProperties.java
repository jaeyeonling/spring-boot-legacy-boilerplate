package com.jaeyeonling.boilerplate.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Setter
@Getter
@Configuration
@ConfigurationProperties(prefix = "jaeyeonling.boilerplate.security")
public class SecurityProperties {

    private String loginEndPoint;
    private String socialLoginEndPoint;
    private int passwordEncodeStrength;
}
