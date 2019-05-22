package com.jaeyeonling.oauth2.service;

import com.jaeyeonling.oauth2.entity.Account;
import com.jaeyeonling.oauth2.entity.Authentication;
import com.jaeyeonling.oauth2.exception.PlatformException;
import com.jaeyeonling.oauth2.exception.PlatformStatus;
import com.jaeyeonling.oauth2.model.SignUpRequest;
import com.jaeyeonling.oauth2.repository.AccountRepository;
import com.jaeyeonling.oauth2.repository.AuthenticationRepository;
import com.jaeyeonling.oauth2.type.AuthProvider;
import com.jaeyeonling.oauth2.type.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.sql.SQLIntegrityConstraintViolationException;

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
