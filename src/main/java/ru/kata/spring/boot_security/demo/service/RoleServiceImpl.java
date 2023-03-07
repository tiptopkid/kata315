package ru.kata.spring.boot_security.demo.service;

import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.repositories.RoleRepository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService{
    private final RoleRepository roleRepository;

    private final EntityManager entityManager;

    public RoleServiceImpl(RoleRepository roleRepository, EntityManager entityManager) {
        this.roleRepository = roleRepository;
        this.entityManager = entityManager;
    }






    @Override
    public void saveRole(Role role) {
        roleRepository.save(role);
    }
}
