package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping("/admin")
    public String showAdminPage() {
        return "admin";
    }

    @GetMapping("/user")
    public String userInfo() {
        return "user";
    }

    @GetMapping("/logout")
    public String logout(){
        return "redirect:/login";
    }

    @GetMapping("/")
    public String startPage() {
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }
}