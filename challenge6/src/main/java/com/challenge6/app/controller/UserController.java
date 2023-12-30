package com.challenge6.app.controller;

import com.challenge6.app.model.dtos.AuthRequestDto;
import com.challenge6.app.model.User;
import com.challenge6.app.model.dtos.UserUpdatePasswordDto;
import com.challenge6.app.service.JwtService;
import com.challenge6.app.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(value = {"/restapi/users"})
public class UserController {
    final UserService userService;
    final JwtService jwtService;
    final AuthenticationManager authenticationManager;

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
    public ResponseEntity<?> deletePasswordUser(@PathVariable UUID id){
        return userService.delete(id);
    }

    @PostMapping("/authenticate")
    public String authenticateAndGetToken(@RequestBody AuthRequestDto authRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authRequest.getUsername(), authRequest.getPassword()));
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(authRequest.getUsername());
        } else {
            throw new UsernameNotFoundException("invalid user request!");
        }
    }
}
