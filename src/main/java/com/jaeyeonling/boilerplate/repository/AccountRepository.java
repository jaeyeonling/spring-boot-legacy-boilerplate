package com.jaeyeonling.boilerplate.repository;

import com.jaeyeonling.boilerplate.entity.Account;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<Account, Long> { }
