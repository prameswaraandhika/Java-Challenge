package com.challenge4.demo.service;


import com.challenge4.demo.model.Order;
import com.challenge4.demo.model.OrderDetail;

import java.util.UUID;

public interface OrderService {
	 void createOrder(Order order);


	 Double getTotalPriceOfOrder();

	 Order getOrderByUserId(UUID userId);

	OrderDetail getOrderDetailById(UUID orderDetailID);
}
