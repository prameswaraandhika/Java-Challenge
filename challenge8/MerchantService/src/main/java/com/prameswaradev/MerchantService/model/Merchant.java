package com.prameswaradev.MerchantService.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

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

	public Merchant(String name, String location) {
		this.name = name;
		this.location = location;
	}
}
