package com.challenge6.app.controller;

import com.challenge6.app.model.User;
import com.challenge6.app.model.dtos.UserNewDto;
import com.challenge6.app.model.dtos.UserUpdatePasswordDto;
import com.challenge6.app.service.AuthService;
import com.challenge6.app.service.UserService;
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
    final UserService userService;

    @PostMapping("/new")
    public ResponseEntity<?> addNewUser(@RequestBody User userInfo) {
        return userService.addUser(userInfo);
    }

    @GetMapping()
    public ResponseEntity<?> getUsers(){
        return userService.findAll();
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
