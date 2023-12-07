package ru.kata.spring.boot_security.demo.services;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;

import java.util.List;
import java.util.Set;

public interface UserService extends UserDetailsService {
    User findByName(String name);
    List<User> getUsers();
    User getUser(long id);
    void save(User user);
    public void save(User user, Set<Role> accessRights);
    void delete(long id);
}
