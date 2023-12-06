package ru.kata.spring.boot_security.demo.utils;

import org.springframework.validation.Validator;
import ru.kata.spring.boot_security.demo.models.User;

public abstract class UserValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }
}
