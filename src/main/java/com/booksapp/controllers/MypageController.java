package com.booksapp.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.booksapp.domain.model.Account;
import com.booksapp.domain.service.AccountService;

@Controller
public class MypageController {
    @Autowired
    private AccountService accountService;
    
    @GetMapping("/mypage")
    public String show(Model model, Principal principal) {
        Account account = accountService.findByEmail(principal.getName());
        model.addAttribute(account);
        return "/mypage/mypage";
    }
}
