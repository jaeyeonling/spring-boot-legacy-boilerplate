package com.jaeyeonling.oauth2.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;
import java.util.List;

@Setter
@Getter
@Configuration
@ConfigurationProperties(prefix = "jaeyeonling.oauth2.jwt")
public class JwtProperties {
    private String secretKey;
    private Duration refreshTerm;
    private Duration expiredTerm;
    private int jwtIdLength;
    private String headerName;

    private List<String> skipPaths;
    private List<String> checkPaths;
}
