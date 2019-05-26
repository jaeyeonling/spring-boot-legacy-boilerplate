package com.jaeyeonling.boilerplate.security.verifier;

import com.jaeyeonling.boilerplate.security.social.github.GithubFetchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GithubVerifier extends SocialVerifier {

    @Autowired
    public GithubVerifier(final GithubFetchService githubFetchService) {
        super(githubFetchService);
    }
}
