package com.challenge6.app.service;

import com.challenge6.app.model.User;
import com.challenge6.app.model.dtos.UserNewDto;
import com.challenge6.app.model.dtos.UserUpdatePasswordDto;
import com.challenge6.app.model.mappers.UserMapper;
import com.challenge6.app.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserService {
    final UserRepository userRepository;
    final UserMapper userMapper;
    final UserRepository repository;
    final PasswordEncoder passwordEncoder;

    public ResponseEntity<?> addUser(User userInfo) {
        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
        User saved = repository.save(userInfo);
        return ResponseEntity.ok(saved);
    }

    public ResponseEntity<List<User>> findAll() {
        List<User> users = userRepository.findAll();
        return ResponseEntity.ok(users);
    }



    public ResponseEntity<User> update(UUID id, UserUpdatePasswordDto userUpdatePasswordDto){
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        User user = userOptional.get();
        user.setPassword(userUpdatePasswordDto.password());

        User userSaved = userRepository.save(user);
        return ResponseEntity.ok(userSaved);
    }

    public ResponseEntity<Void> delete(UUID id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        User user = userOptional.get();
        userRepository.delete(user);
        return ResponseEntity.noContent().build();
    }
}