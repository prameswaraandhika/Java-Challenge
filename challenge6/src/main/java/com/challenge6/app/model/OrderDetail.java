package com.challenge6.app.model;

import com.challenge6.app.model.Order;
import com.challenge6.app.model.Product;
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
	@Column(name = "total_price")
	private Double totalPrice;

	@ManyToOne(targetEntity = Product.class)
	private Product product;

	@ManyToOne(targetEntity = Order.class)
	@JsonIgnore
	private Order order;
}
