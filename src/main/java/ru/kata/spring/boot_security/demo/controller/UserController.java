package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.repositories.RoleRepository;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.List;
import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleRepository roleRepository;



    @GetMapping()
    public String oneUserPage(Principal principal, Model model) {
        User user = userService.findUsersByEmail(principal.getName());
        model.addAttribute("usera", user);
//        List<Role> roles = (List<Role>) roleRepository.findAll();
//
//        model.addAttribute("allroles", roles);
        return "userpage";
    }



}
