package org.example.challenge3.service;

import org.example.challenge3.model.Order;
import org.example.challenge3.model.OrderDetail;

import java.util.List;
import java.util.UUID;

public interface ServiceOrder {
	 void createOrder(Order order);

	 void createOrderDetail(OrderDetail orderDetail);

	 Double getTotalPriceOfOrder();

	 Order getOrderByUserId(UUID userId);

	OrderDetail getOrderDetailById(UUID orderDetailID);
}
