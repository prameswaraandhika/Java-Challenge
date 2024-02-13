package com.challenge4.demo.service;

import com.challenge4.demo.model.User;

import java.util.List;
import java.util.UUID;

public interface UserService {
    List<User> findAll();

    void create(User userSaved);

    void updatePassword(UUID id, String passwordUser);

    void delete(UUID id);
}
