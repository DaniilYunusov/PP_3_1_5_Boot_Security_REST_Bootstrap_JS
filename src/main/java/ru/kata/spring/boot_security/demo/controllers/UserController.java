package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.UserService;
import ru.kata.spring.boot_security.demo.utils.CreateUserValidator;

import java.security.Principal;

@Controller
public class UserController {
    private UserService userService;
    private CreateUserValidator userValidator;

    @Autowired
    public UserController(UserService userService, CreateUserValidator userValidator) {
        this.userService = userService;
        this.userValidator = userValidator;
    }


    @GetMapping("/user")
    public String homePage(Principal principal, Model model) {
        User user = userService.findByName(principal.getName());
        model.addAttribute("user", user);
        return "/users/user";
    }
}
