package com.booksapp.domain.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.booksapp.domain.model.Book;

@Service
public interface BooksService {
    Page<Book> findAll(Pageable pageable);
    
    Book insertOne(Book book);
    
    Book findById(int id);
    
    void deleteById(int id);
    
    Book updateOne(Book book);
}
