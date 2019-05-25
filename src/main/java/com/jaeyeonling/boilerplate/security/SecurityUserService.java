package com.jaeyeonling.boilerplate.security;

import com.jaeyeonling.boilerplate.exception.PlatformException;
import com.jaeyeonling.boilerplate.exception.PlatformStatus;
import com.jaeyeonling.boilerplate.repository.AuthenticationRepository;
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
