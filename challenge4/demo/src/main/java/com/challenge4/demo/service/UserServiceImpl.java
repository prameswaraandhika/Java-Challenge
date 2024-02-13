package com.challenge4.demo.service;

import com.challenge4.demo.model.User;
import com.challenge4.demo.repositori.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService{

    UserRepository userRepository;
    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public void create(User userSaved) {
        userRepository.save(userSaved);
    }

    @Override
    public void updatePassword(UUID id, String passwordUser) {
        Optional<User> user = userRepository.findById(id);
        user.ifPresent(value -> value.setPassword(passwordUser));
        userRepository.save(user.get());
    }

    @Override
    public void delete(UUID id) {
        userRepository.deleteById(id);
    }
}
