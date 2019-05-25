package com.jaeyeonling.boilerplate.repository;

import com.jaeyeonling.boilerplate.entity.Authentication;
import com.jaeyeonling.boilerplate.type.AuthProvider;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AuthenticationRepository extends CrudRepository<Authentication, Long> {

    Optional<Authentication> findByUserId(final String userId);
    Optional<Authentication> findByUserIdAndAuthProvider(
            final String userId,
            final AuthProvider authProvider
    );
}
