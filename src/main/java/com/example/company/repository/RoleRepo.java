package com.example.company.repository;

import com.example.company.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepo extends JpaRepository<Role , Integer> {
    Optional<Role> findByName(String name);
}
