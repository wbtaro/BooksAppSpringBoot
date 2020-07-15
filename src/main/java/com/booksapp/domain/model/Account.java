package com.booksapp.domain.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;

import lombok.Data;

@Data
public class Account {
    @Id Integer id;
    private String email;
    private String password;
    private String name;
    private String description;
    private String role;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
