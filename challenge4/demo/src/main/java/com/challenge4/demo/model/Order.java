package com.challenge4.demo.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Entity
@Table(name = "tbl_order")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	private LocalDate orderTime;
	private String destinationAdress;

	@ManyToOne(targetEntity = User.class)
	@JoinColumn(name = "user_id")
	private User user;

	private boolean complete;

	@OneToMany(mappedBy = "order")
	private List<OrderDetail> orderDetails;
}
