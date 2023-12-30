package com.prameswaradev.OrderService.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Table(name = "tbl_order")
@Entity
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	private String email;
	private LocalDate time;
	private String address;
	private boolean complete;

	public Order(LocalDate time, String address) {
		this.time = time;
		this.address = address;
	}

	//	@ManyToOne(targetEntity = User.class)
//	@JsonIgnore
//	private User user;
//
//	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
//	private Set<OrderDetail> orderDetails;
}
