package com.booksapp.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.booksapp.controllers.helpers.PageHelper;
import com.booksapp.domain.model.Account;
import com.booksapp.domain.model.Book;
import com.booksapp.domain.service.AccountService;
import com.booksapp.domain.service.BooksService;

@Controller
public class MypageController {
    @Autowired
    private AccountService accountService;
    
    @Autowired
    private BooksService booksService;

    @Value("${pageSize}")
    private int pageSize;

    @Value("${maxDisplayedPages}")
    private int maxDisplayedPages;
    
    @GetMapping("/mypage")
    public String index(Model model, @RequestParam(name = "page", defaultValue="0") int page, Principal principal){
        Account account = accountService.findByEmail(principal.getName());

        PageRequest pageReq = PageRequest.of(page, pageSize, Sort.by("updatedAt").descending());
        Page<Book> pageInfo = booksService.findAll(pageReq);
        List<Book> bookList = pageInfo.getContent();

        model.addAttribute("bookList", bookList);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute(account);

        List<Integer> displayedPages = PageHelper.getDisplayedPages(page, maxDisplayedPages, pageInfo.getTotalPages());
        model.addAttribute("displayedPages", displayedPages);

        return "/mypage/mypage";
    }
}
