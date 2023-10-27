package com.challenge4.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_merchant")
public class Merchant {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	private String merchantName;
	private String merchantLocation;
	private boolean isOpen;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "merchant")
	private List<Product> products;
}
