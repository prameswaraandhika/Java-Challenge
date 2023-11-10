package com.challenge5.app.controller;

import com.challenge5.app.model.dtos.UserNewDto;
import com.challenge5.app.model.dtos.UserUpdatePasswordDto;
import com.challenge5.app.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(value = {"/restapi/users"})
public class UserController {
    private final UserService userService;

    @GetMapping()
    public ResponseEntity<?> getUsers(){
        return userService.findAll();
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody UserNewDto userNewDto){
        return userService.create(userNewDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePasswordUser(@PathVariable UUID id, @RequestBody UserUpdatePasswordDto userUpdatePasswordDto){
        return userService.update(id, userUpdatePasswordDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> updatePasswordUser(@PathVariable UUID id){
        return userService.delete(id);
    }

}
