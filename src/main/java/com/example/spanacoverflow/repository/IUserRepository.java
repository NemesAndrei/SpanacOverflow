package com.example.spanacoverflow.repository;

import com.example.spanacoverflow.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface IUserRepository extends JpaRepository<User, Long> {
    public Optional<User> findByUsername(String username);
}
