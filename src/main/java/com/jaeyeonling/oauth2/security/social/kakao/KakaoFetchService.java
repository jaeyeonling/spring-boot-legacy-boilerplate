package com.jaeyeonling.oauth2.security.social.kakao;

import com.jaeyeonling.oauth2.model.Header;
import com.jaeyeonling.oauth2.properties.KakaoApiProperties;
import com.jaeyeonling.oauth2.security.social.SocialFetchService;
import com.jaeyeonling.oauth2.security.social.SocialLoginRequest;
import com.jaeyeonling.oauth2.security.social.SocialUserInfo;
import com.jaeyeonling.oauth2.utils.RestTemplate;
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
