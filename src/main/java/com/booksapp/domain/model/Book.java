package com.booksapp.domain.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;

import lombok.Data;

@Data
public class Book {
    private @Id int id;
    private String title;
    private String author;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    public String toString() {
        return " id: " + String.valueOf(this.id)
               + " title: " + this.title
               + " author: "+ this.author
               + " description: " + this.description
               + " createdAt: " + this.createdAt
               + " updatedAt " + this.updatedAt;
    }
}
