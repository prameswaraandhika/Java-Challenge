package com.challenge4.demo.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "tbl_product")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	private String productName;
	private Double price;


	@ManyToOne(targetEntity = Merchant.class)
    @JoinColumn(name = "merchant_id")
	private Merchant merchant;



}
