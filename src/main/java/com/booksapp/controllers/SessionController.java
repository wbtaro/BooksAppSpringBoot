package com.booksapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SessionController {
    @GetMapping("/login")
    String loginForm() {
        return "/session/login";
    }
}
