package com.booksapp.domain.repository;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.booksapp.domain.model.Account;

@SpringBootTest
public class AccountRepositoryTest {
    @Autowired
    AccountRepository accountRepository;

    @Test
    public void FindByEmail_レコードを取得できる() {
        Account account = accountRepository.findByEmail("a@a.a");
        assertThat(account.getName()).isEqualTo("aaa");
    }

    @Test
    public void FindByEmail_レコードが見つからないのでnullを返す() {
        Account account = accountRepository.findByEmail("q@q.q");
        assertThat(account).isEqualTo(null);
    }
}
