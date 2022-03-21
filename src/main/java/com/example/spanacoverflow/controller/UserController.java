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
public class UserController {


    @Autowired
    UserService userService;

    @RequestMapping(method = RequestMethod.GET, value = "/getAll")
    @ResponseBody
    public List<User> getUsers() {
        return userService.getAllUsers();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/save")
    @ResponseBody
    public User saveUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/update")
    @ResponseBody
    public User updateUser(@RequestBody User user, @RequestParam(name = "userid") Long userId) {
        return userService.updateUser(userId, user);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/delete")
    @ResponseBody
    public String deleteAnswer(@RequestParam(name = "userid") Long userId) {
        return userService.deleteUser(userId);
    }
}
