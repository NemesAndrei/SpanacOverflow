package com.example.spanacoverflow.controller;

import com.example.spanacoverflow.model.Answer;
import com.example.spanacoverflow.model.User;
import com.example.spanacoverflow.service.AnswerService;
import com.example.spanacoverflow.service.AnswerVotesService;
import com.example.spanacoverflow.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/users")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {


    @Autowired
    UserService userService;

    @RequestMapping(method = RequestMethod.GET, value = "/getAll")
    @ResponseBody
    public List<User> getUsers() {
        return userService.getAllUsers();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/save")
    @ResponseBody
    public User saveUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/update")
    @ResponseBody
    public User updateUser(@RequestBody User user, @RequestParam(name = "userid") Long userId) {
        try {
            return userService.updateUser(userId, user);
        } catch (Exception e) {
            return null;
        }

    }

    @RequestMapping(method = RequestMethod.PUT, value = "/ban")
    @ResponseBody
    public User banUser(@RequestParam(name = "userid") Long userId) {
        try {
            return userService.banUser(userId);
        } catch (Exception e) {
            return null;
        }
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/delete")
    @ResponseBody
    public String deleteUser(@RequestParam(name = "userid") Long userId) {
        return userService.deleteUser(userId);
    }
}
