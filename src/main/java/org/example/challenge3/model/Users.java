package org.example.challenge3.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Users {
	private UUID id;
	private String username;
	private String emailAdress;
	private String password;
}
