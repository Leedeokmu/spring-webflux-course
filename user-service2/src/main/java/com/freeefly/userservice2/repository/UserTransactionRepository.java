package com.freeefly.userservice2.repository;

import com.freeefly.userservice2.entity.UserTransaction;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface UserTransactionRepository extends
    ReactiveCrudRepository<UserTransaction, Integer> {

}
