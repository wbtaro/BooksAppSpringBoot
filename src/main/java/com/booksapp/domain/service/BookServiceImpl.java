package com.booksapp.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.booksapp.domain.model.Book;
import com.booksapp.domain.repository.BookRepository;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookRepository bookRepository;
    
    @Override
    public Page<Book> findAll(Pageable pageable){
        return bookRepository.findAll(pageable);
    }
    
    @Override
    public void insertOne(Book book) {
        bookRepository.save(book);
    }
    
    @Override
    public Book findById(int id) {
        Book book = bookRepository.findById(id).get();
        return book;
    }
    
    @Override
    public void deleteById(int id) {
        bookRepository.deleteById(id);
    }
    
    @Override
    public void updateOne(Book book) {
        bookRepository.save(book);
    }
}
