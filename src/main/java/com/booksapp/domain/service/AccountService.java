package com.booksapp.domain.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.booksapp.domain.model.Account;

@Service
public interface AccountService {
    Page<Account> findAll(Pageable pageable);
    Account insertOne(Account account);
    Account updateOne(Account account);
    Account findByEmail(String email);
    Account findById(int id);
}
