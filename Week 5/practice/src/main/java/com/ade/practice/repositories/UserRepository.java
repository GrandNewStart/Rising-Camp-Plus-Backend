package com.ade.practice.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ade.practice.models.User;

public interface UserRepository extends JpaRepository<User, String> {
    public Optional<User> findByName(String name);
}
