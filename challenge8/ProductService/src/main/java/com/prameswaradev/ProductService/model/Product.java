package com.prameswaradev.ProductService.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
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

	private String name;
	private Double price;
	private UUID merchantId;

	public Product(String name, Double price) {
		this.name = name;
		this.price = price;
	}

	//	@JsonIgnore
//	@ManyToOne(targetEntity = Merchant.class)
//	private Merchant merchant;
//
//	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
//	@JsonIgnore
//	private Set<OrderDetail> orderDetail = new HashSet<>();
}
