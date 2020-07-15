package com.booksapp.controllers;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.booksapp.controllers.helpers.PageHelper;
import com.booksapp.domain.model.Account;
import com.booksapp.domain.service.AccountService;

@Controller
public class UsersController {
    @Autowired
    private AccountService accountService;

    @Value("${pageSize}")
    private int pageSize;

    @Value("${maxDisplayedPages}")
    private int maxDisplayedPages;
    
    @GetMapping("/users/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        Account account  = accountService.findById(id);
        model.addAttribute(account);
        return "/users/show";
    }    

    @GetMapping("/users")
    public String index(Model model, @RequestParam(name = "page", defaultValue="0") int page, Principal principal) {
        PageRequest pageReq = PageRequest.of(page, pageSize, Sort.by("email").ascending());
        Page<Account> pageInfo = accountService.findAll(pageReq);
        List<Account> accountList = excludeLoginUser(pageInfo.getContent(), principal);

        model.addAttribute("accountList", accountList);
        model.addAttribute("pageInfo", pageInfo);

        List<Integer> displayedPages = PageHelper.getDisplayedPages(page, maxDisplayedPages, pageInfo.getTotalPages());
        model.addAttribute("displayedPages", displayedPages);
        return "/users/index";
    }

    private List<Account> excludeLoginUser(List<Account> accountList, Principal principal){
        return accountList.stream().filter(account -> {
            if(account.getEmail().equals(principal.getName())) {
                return false;
            }
            return true;
        }).collect(Collectors.toList());
        // 下記のコードは動作しない
        // accountListはUnmmodifiableRandomAccessListなので要素を消せないため。
        // accountList.removeIf(account -> account.getEmail().equals(principal.getName()));
    }
}
