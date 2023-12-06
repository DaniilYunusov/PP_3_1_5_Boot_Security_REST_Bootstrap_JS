package ru.kata.spring.boot_security.demo.utils;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;

@Component
public class UpdateUserValidator extends UserValidator {

    UserRepository userRepository;

    @Autowired
    public UpdateUserValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;

        if(userRepository.findByNameAndIdNot(user.getName(), user.getId()) != null) {
            errors.rejectValue("name", "", "This name is already taken!");
        }
        if(userRepository.findByEmailAndIdNot(user.getEmail(), user.getId()) != null) {
            errors.rejectValue("email", "", "User with with address has already been registered!");
        }
    }
}
