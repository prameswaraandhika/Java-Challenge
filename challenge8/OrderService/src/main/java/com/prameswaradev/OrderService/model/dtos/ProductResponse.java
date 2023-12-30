package com.prameswaradev.OrderService.model.dtos;

public record ProductResponse(
	String name,
	Double price
){
//	public ProductResponse() {
//	}
}

//	@JsonIgnore
//	@ManyToOne(targetEntity = Merchant.class)
//	private Merchant merchant;
//
//	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
//	@JsonIgnore
//	private Set<OrderDetail> orderDetail = new HashSet<>();
