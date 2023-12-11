package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.RoleService;
import ru.kata.spring.boot_security.demo.services.UserService;
import ru.kata.spring.boot_security.demo.utils.CreateUserValidator;
import ru.kata.spring.boot_security.demo.utils.UpdateUserValidator;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;
    private final CreateUserValidator createUserValidator;
    private final UpdateUserValidator updateUserValidator;

    @Autowired
    public AdminController(UserService userService,
                           RoleService roleService,
                           CreateUserValidator createUserValidator,
                           UpdateUserValidator updateUserValidator) {
        this.userService = userService;
        this.roleService = roleService;
        this.createUserValidator = createUserValidator;
        this.updateUserValidator = updateUserValidator;
    }

    @GetMapping()
    public String showUsers(Model model, Principal principal) {
        model.addAttribute("users", userService.getUsers());
        model.addAttribute("currentUser", userService.findByName(principal.getName()));
        model.addAttribute("roles", roleService.getAllRoles());
        return "users/showUsers";
    }

    @GetMapping("/new")
    public String newUser(@ModelAttribute("user") User user, Model model, Principal principal) {
        model.addAttribute("currentUser", userService.findByName(principal.getName()));
        model.addAttribute("roles", roleService.getAllRoles());
        return "users/newUser";
    }

    @PostMapping("/new")
    public String newUserPost(@ModelAttribute("user") @Valid User user, BindingResult bindingResult,
                              Model model, Principal principal) {
        createUserValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors()) {
            model.addAttribute("currentUser", userService.findByName(principal.getName()));
            model.addAttribute("roles", roleService.getAllRoles());
            return "users/newUser";
        }
        userService.save(user);
        return "redirect:/admin";
    }

    @PatchMapping("/update")
    public String updateUserPost(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        updateUserValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors()) {
            return "redirect:/admin";
        }
        userService.save(user);
        return "redirect:/admin";
    }

    @DeleteMapping("/delete")
    public String deleteUser(@RequestParam("id") long id){
        userService.delete(id);
        return "redirect:/admin";
    }

}
