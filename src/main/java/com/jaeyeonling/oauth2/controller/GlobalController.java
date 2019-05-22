package com.jaeyeonling.oauth2.controller;

import com.jaeyeonling.oauth2.entity.Account;
import com.jaeyeonling.oauth2.model.Response;
import com.jaeyeonling.oauth2.model.SignUpRequest;
import com.jaeyeonling.oauth2.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GlobalController {

    private final AccountService accountService;

    //
    //
    //

    @Autowired
    public GlobalController(final AccountService accountService) {
        this.accountService = accountService;
    }

    //
    //
    //

    @PostMapping("/sign-up")
    public ResponseEntity<Account> signUp(
            @RequestBody final SignUpRequest signUpRequest
    ) {
        return Response.ok(accountService.signUp(signUpRequest));
    }

    @GetMapping("/any")
    public ResponseEntity<String> any() {
        return Response.ok("Any");
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<String> user() {
        return Response.ok("User");
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> admin() {
        return Response.ok("Admin");
    }
}
