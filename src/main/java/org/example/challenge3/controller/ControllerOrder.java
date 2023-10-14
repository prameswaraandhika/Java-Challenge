package org.example.challenge3.controller;

import lombok.extern.java.Log;
import org.example.challenge3.model.Order;
import org.example.challenge3.model.OrderDetail;
import org.example.challenge3.model.Product;
import org.example.challenge3.service.ServiceOrder;
import org.example.challenge3.service.ServiceOrderImpl;
import org.example.challenge3.service.ServiceProduct;
import org.example.challenge3.service.ServiceProductImpl;
import org.example.challenge3.sql.InfoUser;
import org.example.challenge3.view.BinarFudView;

import javax.swing.*;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.logging.Logger;

public class ControllerOrder {

	BinarFudView binarFudView;
	ServiceOrder serviceOrder;
	List<OrderDetail> listOrderDetail;


	public ControllerOrder(BinarFudView binarFudView) {
		this.binarFudView = binarFudView;
		this.serviceOrder = new ServiceOrderImpl();
		this.listOrderDetail = new ArrayList<>();
	}

	public void order() {
		try {
			UUID orderId = UUID.randomUUID();
			Order order = Order.builder().id(orderId).userId(InfoUser.USER_ID).orderTime(LocalDate.now()).destinationAdress(InfoUser.DESTINATION).isComplete(true).build();
			serviceOrder.createOrder(order);

//		set orderID of each orderDetail
			for (OrderDetail orderDetail : listOrderDetail) {
				orderDetail.setOrderId(orderId);
				serviceOrder.createOrderDetail(orderDetail);
			}
			System.out.print("Order created Succesfully");
		} catch (Exception e) {
			System.out.print("Order created is not Succesfully: " + e);
		}
	}


	public void addOrderDetail(Product product, int quantity) {
		Double totalPrice = product.getPrice() * quantity;
		listOrderDetail.add(OrderDetail.builder().id(UUID.randomUUID()).productId(product.getId()).quantity(quantity).totalPrice(totalPrice).build());
		updateTotalPrice();
	}

	public void updateTotalPrice() {
		if (!listOrderDetail.isEmpty()) {
			double totalOrderPrice = listOrderDetail.stream().mapToDouble(OrderDetail::getTotalPrice).sum();
			binarFudView.getTotalPesanan().setText("Total: " + converterCurrencyRupiah(totalOrderPrice));
		}
	}

	public void clearOrder(){
		binarFudView.getTableModel().setRowCount(0);
		binarFudView.getTotalPesanan().setText("Total: " + 0);
		listOrderDetail.clear();
	}

	public String converterCurrencyRupiah(double value) {
		// Create a NumberFormat instance for Indonesian currency format
		NumberFormat rupiahFormat = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));

		// Format the value as Indonesian Rupiah
		return rupiahFormat.format(value);
	}
}
