package com.challenge4.demo.controller;



import com.challenge4.demo.model.Order;
import com.challenge4.demo.model.OrderDetail;
import com.challenge4.demo.model.Product;
import com.challenge4.demo.service.OrderService;
import com.challenge4.demo.sql.InfoUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import javax.swing.*;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

@Component
public class OrderController {

	OrderService orderService;
	List<OrderDetail> listOrderDetail;

	@Autowired
	public OrderController(OrderService orderService) {
		this.orderService = orderService;
	}



	public void order() {
		try {
			UUID orderId = UUID.randomUUID();
			Order order = Order.builder().id(orderId).orderTime(LocalDate.now()).destinationAdress(InfoUser.DESTINATION).complete(true).build();
			for (OrderDetail orderDetail:
				 listOrderDetail) {
				order.getOrderDetails().add(orderDetail);
			}
			orderService.createOrder(order);
			JOptionPane.showMessageDialog(null, "Pembayaran selesai.", "Pembayaran Selesai", JOptionPane.INFORMATION_MESSAGE);
			System.out.print("Order created Succesfully");
			clearOrder();
		} catch (Exception e) {
			System.out.print("Order created is not Succesfully: " + e);
		}
	}


	public void addOrderDetail(Product product, int quantity) {
		Double totalPrice = product.getPrice() * quantity;
		listOrderDetail.add(OrderDetail.builder().id(UUID.randomUUID()).quantity(quantity).totalPrice(totalPrice).build());
		updateTotalPrice();
	}

	public void updateTotalPrice() {
		if (!listOrderDetail.isEmpty()) {
			double totalOrderPrice = listOrderDetail.stream().mapToDouble(OrderDetail::getTotalPrice).sum();
		}
	}

	public void clearOrder(){
//		binarFudView.getTableModel().setRowCount(0);
//		binarFudView.getTotalPesanan().setText("Total: " + 0);
		listOrderDetail.clear();
	}

	public String converterCurrencyRupiah(double value) {
		// Create a NumberFormat instance for Indonesian currency format
		NumberFormat rupiahFormat = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));

		// Format the value as Indonesian Rupiah
		return rupiahFormat.format(value);
	}
}
