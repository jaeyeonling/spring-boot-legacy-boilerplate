package com.jaeyeonling.oauth2.repository;

import com.jaeyeonling.oauth2.entity.Account;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<Account, Long> { }
