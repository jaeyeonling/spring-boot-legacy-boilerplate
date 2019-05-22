package com.jaeyeonling.oauth2.security;

import lombok.Setter;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

import javax.persistence.Id;
import java.util.Objects;

@RedisHash
public class AccessToken {

    @Setter
    @Id
    private String id;

    @Setter
    private String token;

    @Setter
    @TimeToLive
    private long timeToLive;

    //
    //
    //

    public boolean verify(final String inputToken) {
        return Objects.equals(inputToken, token);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AccessToken)) {
            return false;
        }

        final var that = (AccessToken) o;

        return Objects.equals(id, that.id) && Objects.equals(token, that.token);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, token);
    }
}
