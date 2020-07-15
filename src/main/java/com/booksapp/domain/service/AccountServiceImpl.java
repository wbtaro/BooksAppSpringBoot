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
    public void insertOne(Account account) {
        if(account.getRole() == null) {
            account.setRole("USER");
        }
        
        if(account.getCreatedAt() == null) {
            account.setCreatedAt(LocalDateTime.now(ZoneOffset.UTC));
        }

        if(account.getPassword().isEmpty()) {
            String password = accountRepository.findById(account.getId()).get().getPassword();
            account.setPassword(password);
        }else {
            account.setPassword(passwordEncoder.encode(account.getPassword()));
        }

        account.setUpdatedAt(LocalDateTime.now(ZoneOffset.UTC));
        accountRepository.save(account);
    }
    
    @Override
    public Account findByEmail(String email) {
        return accountRepository.findByEmail(email);
    }
    
    @Override
    public Account findById(int id) {
        return accountRepository.findById(id).get();
    }
}
