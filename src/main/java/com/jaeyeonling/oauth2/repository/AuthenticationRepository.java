package com.jaeyeonling.oauth2.repository;

import com.jaeyeonling.oauth2.entity.Authentication;
import com.jaeyeonling.oauth2.type.AuthProvider;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AuthenticationRepository extends CrudRepository<Authentication, Long> {

    Optional<Authentication> findByUserId(final String userId);
    Optional<Authentication> findByUserIdAndAuthProvider(
            final String userId,
            final AuthProvider authProvider
    );
}
