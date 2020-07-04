package com.booksapp.domain.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.booksapp.domain.model.Book;

public interface BookRepository extends PagingAndSortingRepository<Book, Integer> {
}
