package ru.kata.spring.boot_security.demo.init;

import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.service.RoleServiceImpl;
import ru.kata.spring.boot_security.demo.service.UserService;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
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
        List<Role> adminRole = new ArrayList<>();
        Role roleUser = new Role("ROLE_USER");
        Role roleAdmin = new Role("ROLE_ADMIN");
        adminRole.add(roleUser);
        adminRole.add(roleAdmin);
        List<Role> userRole = new ArrayList<>();
        userRole.add(roleUser);
        roleService.saveRole(roleUser);
        roleService.saveRole(roleAdmin);

        User admin = new User("admin", "admin", 25, "admin", "admin");
        User user = new User("user", "user", 27, "user", "user");
        admin.setRoles(adminRole);
        user.setRoles(userRole);

        userService.add(admin);
        userService.add(user);




    }
}
