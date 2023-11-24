package com.challenge6.app.service;

import com.challenge6.app.model.User;
import com.challenge6.app.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    final UserRepository repository;
    final PasswordEncoder passwordEncoder;

    public ResponseEntity<?> addUser(User userInfo) {
        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
        User saved = repository.save(userInfo);
        return ResponseEntity.ok(saved);
    }
}
