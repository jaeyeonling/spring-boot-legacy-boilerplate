package com.jaeyeonling.oauth2.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Setter
@Getter
@Configuration
@ConfigurationProperties(prefix = "jaeyeonling.oauth2.application")
public class ApplicationProperties {

    private String name;
    private int version;
}
