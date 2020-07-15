package com.booksapp.controllers;

import java.security.Principal;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.booksapp.controllers.forms.AccountForm;
import com.booksapp.domain.model.Account;
import com.booksapp.domain.service.AccountService;

@Controller
public class AccountController {
    @Autowired
    private ModelMapper modelMapper;
    
    @Autowired
    private AccountService accountService;
   
    @GetMapping("/signup")
    public String createForm(@ModelAttribute AccountForm AccountForm) {
        return "/account/signup";
    }

    @PostMapping("/signup")
    public String create(@ModelAttribute @Validated AccountForm accountForm, BindingResult result, Model model) {
        if(result.hasErrors()) {
            return "/account/signup";
        }

        Account account = modelMapper.map(accountForm, Account.class);
        accountService.insertOne(account);

        return "redirect:/login";
    }

    @GetMapping("/account/edit")
    public String edit(@ModelAttribute AccountForm accountForm, Model model, Principal principal) {
        Account account = accountService.findByEmail(principal.getName());
        modelMapper.map(account, accountForm);
        
        return "/account/edit";
    }
    
    @PostMapping("/account")
    public String update(@ModelAttribute @Validated AccountForm accountForm, BindingResult result, Model model) {
        if(result.hasErrors()) {
            return "/account/edit";
        }

        Account account = modelMapper.map(accountForm, Account.class);
        accountService.insertOne(account);

        return "redirect:/mypage";
    }
}
