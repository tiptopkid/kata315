package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.exception_handling.NoSuchUserException;
import ru.kata.spring.boot_security.demo.exception_handling.UserIncorrectData;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.repositories.RoleRepository;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MyRESTController {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleRepository roleRepository;

    @GetMapping("/users")
    public List<User> showAllUsers() {
        List<User> allUsers = userService.listUsers();
        return allUsers;

    }


    @GetMapping("/users/{id}")
    public User getUser(@PathVariable int id) {
        User user = userService.show(id);

//        if (user == null) {
//            throw new NoSuchUserException("There is no user with ID="
//                    + id + " int Database");
//        }
        return user;
    }

    @PostMapping("/users")
    public User addNewUser(@RequestBody User user) {
        userService.add(user);
        return user;

    }


    @PatchMapping("/users")
    public void updateUser(@RequestBody User user) {
        userService.add(user);

    }


    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id) {
        userService.delete(id);
//        return "User with ID=" + id + " was deleted";
    }







    //ОБРАБОТКА ОШИБОК
//    @ExceptionHandler
//    public ResponseEntity<UserIncorrectData> handleException(NoSuchUserException exception) {
//        UserIncorrectData data = new UserIncorrectData();
//        data.setInfo(exception.getMessage());
//
//        return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);
//    }

//    @ExceptionHandler
//    public ResponseEntity<UserIncorrectData> handleException(Exception exception) {
//        UserIncorrectData data = new UserIncorrectData();
//        data.setInfo(exception.getMessage());
//
//        return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
//    }
}
