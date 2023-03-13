package ru.kata.spring.boot_security.demo.init;

import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.service.RoleServiceImpl;
import ru.kata.spring.boot_security.demo.service.UserService;

import javax.annotation.PostConstruct;
import java.util.*;

@Component
public class DBinit {
    private final UserService userService;
    private final RoleServiceImpl roleService;

    public DBinit(UserService userService, RoleServiceImpl roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @PostConstruct
    private void dataBaseInit() {
        Set<Role> adminRole = new HashSet<>();
        Role roleUser = new Role("ROLE_USER");
        Role roleAdmin = new Role("ROLE_ADMIN");

        adminRole.add(roleAdmin);
        adminRole.add(roleUser);

        Set<Role> userRole = new HashSet<>();
        userRole.add(roleUser);
        userRole.add(roleAdmin);

        roleService.saveRole(roleAdmin);
        roleService.saveRole(roleUser);




        User admin = new User("admin", "admin", 25, "admin@mail.ru", "admin", adminRole);
        User user = new User("user", "user", 27, "user@mail.ru", "user", userRole);
        User tester = new User("test", "test", 32, "test@mail.ru", "test");
//        admin.setRoles(adminRole);
//        user.setRoles(userRole);
        tester.setRoles(adminRole);

        userService.add(admin);
        userService.add(user);
        userService.add(tester);




    }
}
