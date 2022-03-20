package com.example.spanacoverflow.service;

import com.example.spanacoverflow.model.User;
import com.example.spanacoverflow.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    IUserRepository iUserRepository;

    public List<User> getAllUsers() {
        return (List<User>) iUserRepository.findAll();
    }

    public User getUser(Long id) {
        Optional<User> user = iUserRepository.findById(id);
        return user.orElse(null);
    }

    public String deleteUser(Long id) {
        try {
            iUserRepository.delete(this.getUser(id));
            return "Delete success";
        } catch (Exception e) {
            return "Delete failed";
        }
    }

    public User saveUser(User user) {
        return iUserRepository.save(user);
    }

    public User updateUser(Long id, User user) {
        User initialUser = this.getUser(id);
        initialUser.setName(user.getName());
        initialUser.setUsername(user.getUsername());
        initialUser.setPassword(user.getPassword());
        return iUserRepository.save(initialUser);
    }
}
