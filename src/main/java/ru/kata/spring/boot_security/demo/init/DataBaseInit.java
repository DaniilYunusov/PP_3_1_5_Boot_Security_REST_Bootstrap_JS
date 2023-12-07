package ru.kata.spring.boot_security.demo.init;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.repositories.RoleRepository;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;
import ru.kata.spring.boot_security.demo.services.UserService;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.Set;

@Configuration
public class DataBaseInit implements InitializingBean {
    private RoleRepository roleRepository;
    private UserService userService;

    @Autowired
    public DataBaseInit(RoleRepository roleRepository, UserService userService) {
        this.roleRepository = roleRepository;
        this.userService = userService;
    }

    public DataBaseInit() {
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if (roleRepository.findByName("ROLE_ADMIN") == null) {
            Role userRole = new Role("ROLE_USER");
            Role adminRole = new Role("ROLE_ADMIN");
            roleRepository.save(userRole);
            roleRepository.save(adminRole);


            User armin = new User(15, "Armin", "Armin@mail.ru", "sea");
            userService.save(armin);
            User micasa = new User(15, "Micasa", "Micasa@mail.ru", "save_eren");
            userService.save(micasa);
            User eren = new User(15, "Eren", "Eren@mail.ru", "freedom");
            userService.save(eren, Set.of(adminRole, userRole));
        }
    }
}
