package com.jaeyeonling.oauth2.security;

import com.jaeyeonling.oauth2.exception.PlatformException;
import com.jaeyeonling.oauth2.exception.PlatformStatus;
import com.jaeyeonling.oauth2.repository.AuthenticationRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class SecurityUserService implements UserDetailsService {

    private final AuthenticationRepository authenticationRepository;

    //
    //
    //

    public SecurityUserService(final AuthenticationRepository authenticationRepository) {
        this.authenticationRepository = authenticationRepository;
    }

    //
    //
    //

    @Override
    public UserDetails loadUserByUsername(final String userId) {
        return authenticationRepository.findByUserId(userId)
                .map(SecurityUser::new)
                .orElseThrow(
                        () -> PlatformException.builder().status(PlatformStatus.UNAUTHORIZED).build()
                );
    }
}
