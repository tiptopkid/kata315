package ru.kata.spring.boot_security.demo.dao;




import ru.kata.spring.boot_security.demo.models.User;

import java.util.List;

public interface UserDAO {
    void add(User user);
    List<User> listUsers();

    User show(int id);

    void update(int id, User updatedUser);

    void delete(int id);


}
