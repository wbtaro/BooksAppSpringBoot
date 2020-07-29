package com.booksapp.domain.service;


import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import com.booksapp.domain.model.Account;

@SpringBootTest
@Transactional
public class AccountServiceTest {
    @Autowired
    private AccountService accountService;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;
    
    @Test
    public void findAll_1ページ目を取得する() {
        int page = 0;
        int pageSize = 2;
        PageRequest pageReq = PageRequest.of(page, pageSize, Sort.by("email").ascending());
        Page<Account> pageInfo = accountService.findAll(pageReq);
        assertThat(pageInfo.getContent().get(0).getEmail()).isEqualTo("a@a.a");
        assertThat(pageInfo.getContent().get(1).getEmail()).isEqualTo("b@b.b");
        assertThat(pageInfo.getSize()).isEqualTo(2);
        assertThat(pageInfo.getNumber()).isEqualTo(0);
    }

    @Test
    public void findAll_2ページ目を取得する() {
        int page = 1;
        int pageSize = 2;
        PageRequest pageReq = PageRequest.of(page, pageSize, Sort.by("email").ascending());
        Page<Account> actual = accountService.findAll(pageReq);
        assertThat(actual.getContent().get(0).getEmail()).isEqualTo("c@c.c");
        assertThat(actual.getContent().get(1).getEmail()).isEqualTo("d@d.d");
        assertThat(actual.getSize()).isEqualTo(2);
        assertThat(actual.getNumber()).isEqualTo(1);
    }

    @Test
    public void findByEmail_レコードを取得できる() {
        Account actual = accountService.findByEmail("a@a.a");
        assertThat(actual.getName()).isEqualTo("aaa");
    }

    @Test
    public void findByEmail_レコードが見つからないのでnullを返す() {
        Account actual = accountService.findByEmail("q@q.q");
        assertThat(actual).isEqualTo(null);
    }

    @Test
    public void findById_レコードを取得できる() {
        Account actual = accountService.findById(1);
        assertThat(actual.getEmail()).isEqualTo("a@a.a");
    }

    @Test
    public void findById_レコードが見つからないのでnullを返す() {
        Account actual = accountService.findById(9999);
        assertThat(actual).isEqualTo(null);
    }
    
    @Test
    public void insertOne_新規作成() {
        Account account = new Account();
        account.setEmail("p@p.p");
        account.setName("ppp");
        account.setDescription("ppp");
        account.setPassword("test");
        
        Account actual = accountService.insertOne(account);
        assertThat(actual.getId()).isNotEqualTo(0);
        assertThat(actual.getEmail()).isEqualTo("p@p.p");
        assertThat(actual.getName()).isEqualTo("ppp");
        assertThat(actual.getDescription()).isEqualTo("ppp");
        assertTrue(passwordEncoder.matches("test", actual.getPassword()));
        assertThat(actual.getCreatedAt()).isBetween(LocalDateTime.now(ZoneOffset.UTC).minusMinutes(1), LocalDateTime.now(ZoneOffset.UTC));
        assertThat(actual.getUpdatedAt()).isBetween(LocalDateTime.now(ZoneOffset.UTC).minusMinutes(1), LocalDateTime.now(ZoneOffset.UTC));
    }
    
    @Test
    public void updateOne_1件更新_パスワード変更なし() {
        Account account = new Account();
        account.setId(1);
        account.setEmail("p@p.p");
        account.setName("ppp");
        account.setDescription("ppp");
 
        Account actual = accountService.updateOne(account);

        assertThat(actual.getId()).isNotEqualTo(0);
        assertThat(actual.getEmail()).isEqualTo("a@a.a");
        assertThat(actual.getName()).isEqualTo("ppp");
        assertThat(actual.getDescription()).isEqualTo("ppp");
        assertTrue(passwordEncoder.matches("test", actual.getPassword()));
        assertThat(actual.getCreatedAt()).isEqualTo(LocalDateTime.of(2015, 1, 1, 0, 0));
        assertThat(actual.getUpdatedAt()).isBetween(LocalDateTime.now(ZoneOffset.UTC).minusMinutes(1), LocalDateTime.now(ZoneOffset.UTC));
    }

    @Test
    public void updateOne_1件更新_パスワード変更あり() {
        Account account = new Account();
        account.setId(1);
        account.setEmail("p@p.p");
        account.setPassword("change");
        account.setName("ppp");
        account.setDescription("ppp");
 
        Account actual = accountService.updateOne(account);

        assertThat(actual.getId()).isNotEqualTo(0);
        assertThat(actual.getEmail()).isEqualTo("a@a.a");
        assertThat(actual.getName()).isEqualTo("ppp");
        assertThat(actual.getDescription()).isEqualTo("ppp");
        assertTrue(passwordEncoder.matches("change", actual.getPassword()));
        assertThat(actual.getCreatedAt()).isEqualTo(LocalDateTime.of(2015, 1, 1, 0, 0));
        assertThat(actual.getUpdatedAt()).isBetween(LocalDateTime.now(ZoneOffset.UTC).minusMinutes(1), LocalDateTime.now(ZoneOffset.UTC));
    }
}
