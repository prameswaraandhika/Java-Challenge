package com.challenge4.demo.controller;

import com.challenge4.demo.model.User;
import com.challenge4.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class UserController {

    @Autowired
    private UserService userService;

    public List<User> getAllUsers() {
        return userService.findAll();
    }


    public void create(User userSaved) {
        userService.create(userSaved);
    }

    public void update(UUID id, String passwordUser) {
        userService.updatePassword(id, passwordUser);
    }

    public void delete(UUID id) {
        userService.delete(id);
    }
}
