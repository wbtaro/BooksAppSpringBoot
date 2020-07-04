package com.booksapp.domain.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.booksapp.domain.model.Book;

@Service
public interface BookService {
    Page<Book> findAll(Pageable pageable);
    
    void insertOne(Book book);
    
    Book findById(int id);
    
    void deleteById(int id);
    
    void updateOne(Book book);
}
