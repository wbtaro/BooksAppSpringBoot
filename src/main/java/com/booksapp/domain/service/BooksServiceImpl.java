package com.booksapp.domain.service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.booksapp.domain.model.Book;
import com.booksapp.domain.repository.BookRepository;

@Service
public class BooksServiceImpl implements BooksService {
    @Autowired
    private BookRepository bookRepository;
    
    @Override
    public Page<Book> findAll(Pageable pageable){
        return bookRepository.findAll(pageable);
    }
    
    @Override
    public Book insertOne(Book book) {
        book.setCreatedAt(LocalDateTime.now(ZoneOffset.UTC));
        book.setUpdatedAt(LocalDateTime.now(ZoneOffset.UTC));
        bookRepository.save(book);
        return book;
    }
    
    @Override
    public Book findById(int id) {
        Book book = bookRepository.findById(id).orElse(null);
        return book;
    }
    
    @Override
    public void deleteById(int id) {
        bookRepository.deleteById(id);
    }
    
    @Override
    public Book updateOne(Book book) {
        Book oldBook = findById(book.getId());
        book.setUpdatedAt(LocalDateTime.now(ZoneOffset.UTC));
        book.setCreatedAt(oldBook.getCreatedAt());
        bookRepository.save(book);
        return book;
    }
}
