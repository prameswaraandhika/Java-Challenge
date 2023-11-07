package com.challenge5.app.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "tbl_product")
@Entity
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	private String productName;
	private Double price;
	@ManyToOne(targetEntity = Merchant.class)
	private Merchant merchant;

	@OneToMany(mappedBy = "product")
	private Set<OrderDetail> orderDetail;
}
