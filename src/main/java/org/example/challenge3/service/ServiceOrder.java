package org.example.challenge3.service;

import org.example.challenge3.model.Order;
import org.example.challenge3.model.OrderDetail;

import java.util.List;

public interface ServiceOrder {
	public void createOrder(Order order);

	public double getTotalPriceOfOrder();
}
