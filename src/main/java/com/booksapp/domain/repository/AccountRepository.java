package com.booksapp.domain.repository;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.booksapp.domain.model.Account;

public interface AccountRepository extends PagingAndSortingRepository<Account, Integer> {
    @Query("SELECT * FROM account WHERE email = :email")
    Account findByEmail(String email);
}
