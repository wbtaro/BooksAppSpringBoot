package com.booksapp.controllers;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.booksapp.controllers.forms.BookForm;
import com.booksapp.controllers.helpers.PageHelper;
import com.booksapp.domain.model.Book;
import com.booksapp.domain.service.BookService;

@Controller
public class BooksController {
    @Autowired
    BookService bookService;

    @Autowired
    ModelMapper modelMapper;

    @Value("${pageSize}")
    private int pageSize;

    @Value("${maxDisplayedPages}")
    private int maxDisplayedPages;

    @GetMapping("/books")
    public String index(Model model, @RequestParam(name = "page", defaultValue="0") int page){
        PageRequest pageReq = PageRequest.of(page, pageSize, Sort.by("updatedAt").descending());
        Page<Book> pageInfo = bookService.findAll(pageReq);
        List<Book> bookList = pageInfo.getContent();
        for(var book : bookList) {
            System.out.println(book);
        }

        model.addAttribute("bookList", bookList);
        model.addAttribute("pageInfo", pageInfo);

        List<Integer> displayedPages = PageHelper.getDisplayedPages(page, maxDisplayedPages, pageInfo.getTotalPages());
        model.addAttribute("displayedPages", displayedPages);

        return "/books/index";
    }

    @GetMapping("/books/new")
    public String createForm(@ModelAttribute BookForm bookForm) {
        return "/books/new";
    }

    @PostMapping("/books")
    public String create(@Validated BookForm bookForm, BindingResult result, Model model) {
        model.addAttribute("bookForm", bookForm);
        System.out.println(bookForm);
        System.out.println(result);
        if(result.hasErrors()) {
            return "/books/new";
        }

        Book book = modelMapper.map(bookForm, Book.class);
        book.setCreatedAt(LocalDateTime.now(ZoneOffset.UTC));
        book.setUpdatedAt(LocalDateTime.now(ZoneOffset.UTC));
        bookService.insertOne(book);

        return "redirect:/books";
    }

    @GetMapping("/books/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        Book book = bookService.findById(id);
        model.addAttribute("book", book);

        return "/books/show";
    }

    @DeleteMapping("/books/{id}")
    public String delete(@PathVariable("id") int id) {
        bookService.deleteById(id);

        return "redirect:/books";
    }

    @GetMapping("/books/{id}/edit")
    public String edit(@PathVariable("id") int id, @ModelAttribute BookForm bookForm, Model model) {
        Book book = bookService.findById(id);
        modelMapper.map(book, bookForm);
        model.addAttribute("bookId", id);

        return "books/edit";
    }

    @PatchMapping("/books/{id}")
    public String update(@PathVariable("id") int id,  @ModelAttribute @Validated BookForm bookForm, BindingResult result){
        if(result.hasErrors()) {
            return "/books/new";
        }

        Book book = modelMapper.map(bookForm, Book.class);
        book.setId(id);
        book.setUpdatedAt(LocalDateTime.now(ZoneOffset.UTC));
        bookService.updateOne(book);

        return "redirect:/books";
    }
}
