package com.challenge5.app.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tbl_merchant")
@Accessors(chain = true)
@Entity
public class Merchant {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	private String name;

	private String location;

	private boolean open;

	@OneToMany(mappedBy = "merchant", cascade = CascadeType.ALL)
	private Set<Product> products;
}
