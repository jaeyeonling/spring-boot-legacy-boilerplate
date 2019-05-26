package com.jaeyeonling.boilerplate.security.social.naver;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter(AccessLevel.PROTECTED)
class NaverResponse {

    private String id;

    private String nickname;

    private String email;
}
