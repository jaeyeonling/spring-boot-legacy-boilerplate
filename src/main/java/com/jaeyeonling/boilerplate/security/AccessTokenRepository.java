package com.jaeyeonling.boilerplate.security;

import org.springframework.data.repository.CrudRepository;

public interface AccessTokenRepository extends CrudRepository<AccessToken, String> { }
