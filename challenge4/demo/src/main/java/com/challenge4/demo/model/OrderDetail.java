package com.challenge4.demo.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "tbl_order_detail")
public class OrderDetail {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;

	@ManyToOne(targetEntity = Order.class)
	@JoinColumn(name = "order_id")
	private Order order;

	@ManyToOne(targetEntity = Product.class)
	@JoinColumn(name = "product_id")
	private Product product;

	private Integer quantity;
	private Double totalPrice;

}
