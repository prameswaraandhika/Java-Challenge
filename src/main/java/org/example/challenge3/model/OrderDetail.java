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
public class OrderDetail {
	private UUID id;
	private UUID orderId;
	private UUID productId;
	private Integer quantity;


}
