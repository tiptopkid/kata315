package ru.kata.spring.boot_security.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.repositories.RoleRepository;
import ru.kata.spring.boot_security.demo.service.UserService;


import java.security.Principal;

import java.util.HashSet;
import java.util.Set;


@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private UserService userService;

    @Autowired
    private RoleRepository roleRepository;




//    @GetMapping
//    public String userPage(Model model) {
//
//        model.addAttribute("users", userService.listUsers());
//        return "users";
//    }

//    @GetMapping("/user")
//    public String oneUserPage(Principal principal, Model model) {
//        User user = userService.findUsersByEmail(principal.getName());
//        model.addAttribute("usera", user);
//
//        return "userpage";
//    }

    @GetMapping()
    public String userPage2(Principal principal, Model model) {

        model.addAttribute("newUser", new User());
        Set<Role> roles = new HashSet<>(roleRepository.findAll());
        model.addAttribute("allRoles", roles);

//        model.addAttribute("editUser", userService.show(id));

        User user = userService.findUsersByEmail(principal.getName());
        model.addAttribute("usera", user);

        model.addAttribute("users", userService.listUsers());
        return "adminpage";
    }



    @GetMapping("/new")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        Set<Role> roles = new HashSet<>(roleRepository.findAll());

        model.addAttribute("allroles", roles);
        return "/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("user") User user) {
        userService.add(user);
        return "redirect:/admin";
    }

//    @GetMapping("/{id}/edit")
//    public String edit(@PathVariable("id") int id, Model model) {
//        model.addAttribute("user", userService.show(id));
//        Set<Role> roles = new HashSet<>(roleRepository.findAll());
//
//        model.addAttribute("allroles", roles);
//
//        return "/edit";
//
//    }

    @PatchMapping(value = "/test2/{id}")
    public String updateUser(@ModelAttribute("user") User updatedUser, @PathVariable("id") int id) {

        userService.updateUser(updatedUser, id);
        return "redirect:/admin";
    }
//    @PatchMapping("/{id}")
//    public String update(@ModelAttribute("user") User user, @PathVariable("id") int id) {
//
////user.setRoles(roles);
//
//
//        userService.update(user);
//        return "redirect:/admin";
//    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        userService.delete(id);
        return "redirect:/admin";
    }


}
