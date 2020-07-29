package com.booksapp.domain.service;


import static org.assertj.core.api.Assertions.*;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import com.booksapp.domain.model.Book;

@SpringBootTest
@Transactional
public class BooksServiceTest {
    @Autowired
    private BooksService booksService;

    @Test
    public void findAll_1ページ目を取得する() {
        int page = 0;
        int pageSize = 2;
        PageRequest pageReq = PageRequest.of(page, pageSize, Sort.by("updatedAt").ascending());
        Page<Book> pageInfo = booksService.findAll(pageReq);
        assertThat(pageInfo.getContent().get(0).getTitle()).isEqualTo("harry");
        assertThat(pageInfo.getContent().get(1).getTitle()).isEqualTo("ok");
        assertThat(pageInfo.getSize()).isEqualTo(2);
        assertThat(pageInfo.getNumber()).isEqualTo(0);
    }

    @Test
    public void findAll_2ページ目を取得する() {
        int page = 1;
        int pageSize = 2;
        PageRequest pageReq = PageRequest.of(page, pageSize, Sort.by("updatedAt").ascending());
        Page<Book> actual = booksService.findAll(pageReq);
        assertThat(actual.getContent().get(0).getTitle()).isEqualTo("kaimono");
        assertThat(actual.getContent().get(1).getTitle()).isEqualTo("potter");
        assertThat(actual.getSize()).isEqualTo(2);
        assertThat(actual.getNumber()).isEqualTo(1);
    }

    @Test
    public void findById_レコードを取得できる() {
        Book actual = booksService.findById(1);
        assertThat(actual.getTitle()).isEqualTo("harry");
    }

    @Test
    public void findById_レコードが見つからないのでnullを返す() {
        Book actual = booksService.findById(9999);
        assertThat(actual).isEqualTo(null);
    }
    
    @Test
    public void insertOne_新規作成() {
        Book book = new Book();
        book.setTitle("yakusoku");
        book.setAuthor("higashino");
        book.setDescription("okubukai");
        
        Book actual = booksService.insertOne(book);
        assertThat(actual.getId()).isNotEqualTo(0);
        assertThat(actual.getTitle()).isEqualTo("yakusoku");
        assertThat(actual.getAuthor()).isEqualTo("higashino");
        assertThat(actual.getDescription()).isEqualTo("okubukai");
        assertThat(actual.getCreatedAt()).isBetween(LocalDateTime.now(ZoneOffset.UTC).minusMinutes(1), LocalDateTime.now(ZoneOffset.UTC));
        assertThat(actual.getUpdatedAt()).isBetween(LocalDateTime.now(ZoneOffset.UTC).minusMinutes(1), LocalDateTime.now(ZoneOffset.UTC));
    }

    @Test
    public void updateOne_1件更新() {
        Book book = new Book();
        book.setId(1);
        book.setTitle("kenjanoishi");
        book.setAuthor("rolling");
        book.setDescription("fantastic");
        
        Book actual = booksService.updateOne(book);
        assertThat(actual.getId()).isEqualTo(1);
        assertThat(actual.getTitle()).isEqualTo("kenjanoishi");
        assertThat(actual.getAuthor()).isEqualTo("rolling");
        assertThat(actual.getDescription()).isEqualTo("fantastic");
        assertThat(actual.getCreatedAt()).isEqualTo(LocalDateTime.of(2000, 1, 1, 0, 0));
        assertThat(actual.getUpdatedAt()).isBetween(LocalDateTime.now(ZoneOffset.UTC).minusMinutes(1), LocalDateTime.now(ZoneOffset.UTC));
    }
    
    // deleteByIdは特に値を返さず、他の操作もしないため、テストは省略
}
