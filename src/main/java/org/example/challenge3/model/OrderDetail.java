package org.example.challenge3.model;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
public class OrderDetail {
	private UUID id;
	private UUID orderId;
	private UUID productId;
	private Integer quantity;
	private Double totalPrice;


}
