package com.challenge6.app.model;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AuthRequestDto {
    private String username;
    private String password;


}
