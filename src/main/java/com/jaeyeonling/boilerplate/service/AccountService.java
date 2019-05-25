package com.jaeyeonling.boilerplate.service;

import com.jaeyeonling.boilerplate.entity.Account;
import com.jaeyeonling.boilerplate.entity.Authentication;
import com.jaeyeonling.boilerplate.exception.PlatformException;
import com.jaeyeonling.boilerplate.exception.PlatformStatus;
import com.jaeyeonling.boilerplate.model.SignUpRequest;
import com.jaeyeonling.boilerplate.repository.AuthenticationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    private final AuthenticationRepository authenticationRepository;

    //
    //
    //

    @Autowired
    public AccountService(final AuthenticationRepository authenticationRepository) {
        this.authenticationRepository = authenticationRepository;
    }

    //
    //
    //

    public Account signUp(final SignUpRequest signUpRequest) {
        final var authentication = Authentication.of(signUpRequest);

        try {
            authenticationRepository.save(authentication);
        } catch (final DataIntegrityViolationException e) {
            throw PlatformException.builder().status(PlatformStatus.ALREADY_EXISTS_USER).build();
        }

        return authentication.getAccount();
    }
}