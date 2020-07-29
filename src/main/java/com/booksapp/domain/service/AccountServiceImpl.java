package com.booksapp.domain.service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.booksapp.domain.model.Account;
import com.booksapp.domain.repository.AccountRepository;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;
 
    @Override
    public Page<Account> findAll(Pageable pageable){
        return accountRepository.findAll(pageable);
    }

    @Override
    public Account insertOne(Account account) {
        account.setRole("USER");
        account.setCreatedAt(LocalDateTime.now(ZoneOffset.UTC));
        account.setUpdatedAt(LocalDateTime.now(ZoneOffset.UTC));
        account.setPassword(passwordEncoder.encode(account.getPassword()));

        accountRepository.save(account);
        
        return account;
    }

    @Override
    public Account updateOne(Account account) {
        Account oldAccount = accountRepository.findById(account.getId()).get();
        Account newAccount = new Account();

        newAccount.setId(oldAccount.getId());
        newAccount.setEmail(oldAccount.getEmail());
        newAccount.setCreatedAt(oldAccount.getCreatedAt());

        newAccount.setName(account.getName());
        newAccount.setDescription(account.getDescription());

        if(account.getPassword() == null) {
            newAccount.setPassword(oldAccount.getPassword());
        }else {
            newAccount.setPassword(passwordEncoder.encode(account.getPassword()));
        }

        newAccount.setUpdatedAt(LocalDateTime.now(ZoneOffset.UTC));
        accountRepository.save(newAccount);
        
        return newAccount;
    }

    @Override
    public Account findByEmail(String email) {
        return accountRepository.findByEmail(email);
    }

    @Override
    public Account findById(int id) {
        return accountRepository.findById(id).orElse(null);
    }
}
