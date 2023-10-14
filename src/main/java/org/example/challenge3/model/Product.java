package org.example.challenge3.model;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {
	private UUID id;
	private String productName;
	private Double price;
	private UUID merchantId;

}
