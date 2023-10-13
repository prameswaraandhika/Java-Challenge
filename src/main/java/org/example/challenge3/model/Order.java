package org.example.challenge3.model;

import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Order {
	private UUID id;
	private LocalDate orderTime;
	private String destinationAdress;
	private UUID userId;
	private boolean isComplete;
}
