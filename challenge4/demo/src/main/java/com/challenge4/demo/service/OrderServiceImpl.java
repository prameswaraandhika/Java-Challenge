package com.challenge4.demo.service;


import com.challenge4.demo.model.Order;
import com.challenge4.demo.model.OrderDetail;
import com.challenge4.demo.repositori.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	OrderRepository orderRepository;

	@Override
	public void createOrder(Order order) {
		orderRepository.save(order);
	}


	@Override
	public Double getTotalPriceOfOrder() {
		return null;
	}

	@Override
	public Order getOrderByUserId(UUID userId) {
		return null;
	}

	@Override
	public OrderDetail getOrderDetailById(UUID orderDetailId) {
		return null;
	}


}
