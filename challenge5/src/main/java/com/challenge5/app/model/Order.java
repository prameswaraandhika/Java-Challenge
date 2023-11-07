package com.challenge5.app.model;

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
	private LocalDate orderTime;
	private String destinationAdress;
	private boolean isComplete;

	@ManyToOne(targetEntity = User.class)
	private User user;

	@OneToMany(mappedBy = "order")
	private Set<OrderDetail> orderDetails;
}
