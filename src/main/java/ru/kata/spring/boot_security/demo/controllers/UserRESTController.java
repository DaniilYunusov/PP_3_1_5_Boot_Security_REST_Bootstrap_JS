package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.RoleService;
import ru.kata.spring.boot_security.demo.services.UserService;
import ru.kata.spring.boot_security.demo.utils.*;


@RestController
@RequestMapping("/user")
public class UserRESTController {
    private final UserService userService;
    private final RoleService roleService;
    private final CreateUserValidator createUserValidator;
    private final UpdateUserValidator updateUserValidator;


    @Autowired
    public UserRESTController(UserService userService, RoleService roleService, CreateUserValidator createUserValidator, UpdateUserValidator updateUserValidator) {
        this.userService = userService;
        this.roleService = roleService;
        this.createUserValidator = createUserValidator;
        this.updateUserValidator = updateUserValidator;
    }

    @GetMapping("/userList")
    public ResponseEntity<User> getUserInfo(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(user);
    }
}
