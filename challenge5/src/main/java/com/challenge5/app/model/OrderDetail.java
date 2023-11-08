package com.challenge5.app.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "tbl_order_detail")
@Entity
public class OrderDetail {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	private Integer quantity;
	private Double totalPrice;

	@JsonIgnore
	@ManyToOne(targetEntity = Product.class)
	private Product product;

	@ManyToOne(targetEntity = Order.class)
	private Order order;
}
