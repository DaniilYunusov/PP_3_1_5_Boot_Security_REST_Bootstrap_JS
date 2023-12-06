package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.UserService;
import ru.kata.spring.boot_security.demo.utils.CreateUserValidator;
import ru.kata.spring.boot_security.demo.utils.UpdateUserValidator;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private UserService userService;
    private CreateUserValidator createUserValidator;
    private UpdateUserValidator updateUserValidator;

    @Autowired
    public AdminController(UserService userService,
                           CreateUserValidator createUserValidator,
                           UpdateUserValidator updateUserValidator) {
        this.userService = userService;
        this.createUserValidator = createUserValidator;
        this.updateUserValidator = updateUserValidator;
    }

    @GetMapping()
    public String showUsers(Model model) {
        model.addAttribute("users", userService.getUsers());
        return "users/showUsers";
    }

    @GetMapping("/new")
    public String newUser(@ModelAttribute("user") User user) {
        return "users/newUser";
    }

    @PostMapping("/new")
    public String newUserPost(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        createUserValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors()) {
            return "users/newUser";
        }
        userService.save(user);
        return "redirect:/admin";
    }

    @GetMapping("/update")
    public String updateUser(@RequestParam("id") long id, Model model){
        model.addAttribute("user", userService.getUser(id));
        return "users/updateUser";
    }

    @PostMapping("/update")
    public String updateUserPost(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        updateUserValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors()) {
            return "users/updateUser";
        }
        userService.save(user);
        return "redirect:/admin";
    }

    @GetMapping("/delete")
    public String deleteUser(@RequestParam("id") long id){
        userService.delete(id);
        return "redirect:/admin";
    }

}
