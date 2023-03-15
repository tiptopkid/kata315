package ru.kata.spring.boot_security.demo.service;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImp implements UserService, UserDetailsService {


    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImp(UserRepository userRepository,@Lazy PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    @Transactional
    public void add(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);


    }

    @Transactional(readOnly = true)
    @Override
    public List<User> listUsers() {
        return userRepository.findAll();

    }

    @Transactional(readOnly = true)
    @Override
    public User show(int id) {
//        User result = userRepository.getById(id);
//        Hibernate.initialize(result.getRoles());
//        return result;


        return userRepository.getById(id);

    }

    @Transactional
    @Override
    public void update(User updatedUser) {

        updatedUser.setPassword(passwordEncoder.encode(show(updatedUser.getId()).getPassword()));
//updatedUser.setPassword(passwordEncoder.encode("123"));
        userRepository.save(updatedUser);


    }
//TODO
    @Override
    @Transactional
    public void updateUser(User updatedUser, int id) {
        User existingUser = show(id);

        existingUser.setFirstName(updatedUser.getFirstName());
        existingUser.setEmail(updatedUser.getEmail());
        existingUser.setLastname(updatedUser.getLastname());
        existingUser.setAge(updatedUser.getAge());
        existingUser.setRoles(updatedUser.getRoles());

        if (!updatedUser.getPassword().isEmpty()) {
            existingUser.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
        }

        userRepository.save(existingUser);
    }


    @Transactional
    @Override
    public void delete(int id) {

        userRepository.deleteById(id);
    }



    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails user = findUsersByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("User '%s' not found", username));
        }

        return user;
    }

    @Override
    public User findUsersByEmail(String email) {
        return userRepository.findUsersByEmail(email);
    }


}
