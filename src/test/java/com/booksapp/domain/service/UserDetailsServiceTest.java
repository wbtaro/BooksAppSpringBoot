package com.booksapp.domain.service;


import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
public class UserDetailsServiceTest {
    @Autowired
    private UserDetailsService userDetailsService;
    
    @Autowired
    BCryptPasswordEncoder passwordEncoder;
    
    @Test
    public void loadUserByUsername_ユーザーが見つかる() {
        UserDetails actual = userDetailsService.loadUserByUsername("a@a.a");

        assertThat(actual.getUsername()).isEqualTo("a@a.a");
        assertTrue(passwordEncoder.matches("test", actual.getPassword()));
        assertTrue(actual.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_USER")));
    }

    @Test
    public void loadUserByUsername_ユーザーが見つからず例外が発生する() {
        assertThrows(UsernameNotFoundException.class, () -> userDetailsService.loadUserByUsername("q@q.q"));
    }
}
