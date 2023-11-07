package com.challenge5.app.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tbl_merchant")
@Accessors(chain = true)
@Entity
public class Merchant {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	private String merchantName;
	private String merchantLocation;
	private boolean isOpen;

	@OneToMany(mappedBy = "merchant")
	private Set<Product> products;
}
