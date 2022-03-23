package com.example.spanacoverflow;

import com.example.spanacoverflow.model.User;
import com.example.spanacoverflow.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@SpringBootTest
@Transactional
public class UserTest {

    @Autowired
    UserService userService;

    @Test
    void testSaveUser() {
        User user = new User();
        user.setName("Andrei");
        user.setUsername("andreinemes");
        user.setPassword("nuare");
        userService.saveUser(user);
        assert (userService.findByUsername("andreinemes").getName().equals("Andrei"));
    }

    @Test
    void testUpdateUser() {
        User user = new User();
        user.setName("Andrei");
        user.setUsername("andreinemes");
        user.setPassword("nuare");
        User savedUser = userService.saveUser(user);
        user = new User();
        user.setName("Andreixd");
        user.setUsername("andreinemes");
        user.setPassword("nuarexd");
        userService.updateUser(savedUser.getId(), user);
        assert (userService.findByUsername("andreinemes").getName().equals("Andreixd"));
    }

    @Test
    void testDeleteUser() {
        User user = new User();
        user.setName("Andrei");
        user.setUsername("andreinemes");
        user.setPassword("nuare");
        User savedUser = userService.saveUser(user);
        String s = userService.deleteUser(savedUser.getId());
        assert Objects.equals(s,"Delete success");
        assert (userService.getUser(savedUser.getId())==null);
    }
}
