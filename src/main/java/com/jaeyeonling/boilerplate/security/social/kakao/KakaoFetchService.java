package com.jaeyeonling.boilerplate.security.social.kakao;

import com.jaeyeonling.boilerplate.model.Header;
import com.jaeyeonling.boilerplate.properties.KakaoApiProperties;
import com.jaeyeonling.boilerplate.security.social.SocialFetchService;
import com.jaeyeonling.boilerplate.security.social.SocialLoginRequest;
import com.jaeyeonling.boilerplate.security.social.SocialUserInfo;
import com.jaeyeonling.boilerplate.utils.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

@Service
public class KakaoFetchService implements SocialFetchService {

    private final KakaoApiProperties kakaoApiProperties;
    private final RestTemplate restTemplate;

    //
    //
    //

    @Autowired
    public KakaoFetchService(
            final KakaoApiProperties kakaoApiProperties,
            final RestTemplate restTemplate
    ) {
        this.kakaoApiProperties = kakaoApiProperties;
        this.restTemplate = restTemplate;
    }

    //
    //
    //

    @Override
    public SocialUserInfo getSocialUserInfo(final SocialLoginRequest socialLoginRequest) {
        final var header = new Header();
        header.setBearerToken(socialLoginRequest.getToken());

        final var entity = new HttpEntity<>(null, header);

        final var result = restTemplate.exchange(
                kakaoApiProperties.getEndPoint(),
                HttpMethod.GET,
                entity,
                KakaoUserInfo.class
        );

        // TODO: check response status code

        return result.getBody();
    }
}
