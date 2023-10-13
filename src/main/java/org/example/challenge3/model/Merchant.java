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
public class Merchant {
	private UUID id;
	private String merchantName;
	private String merchantLocation;
	private boolean isOpen;

}
