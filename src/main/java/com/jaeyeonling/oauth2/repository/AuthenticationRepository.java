package com.jaeyeonling.oauth2.repository;

import com.jaeyeonling.oauth2.entity.Authentication;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AuthenticationRepository extends CrudRepository<Authentication, Long> {
    Optional<Authentication> findByUserId(final String userId);
}
