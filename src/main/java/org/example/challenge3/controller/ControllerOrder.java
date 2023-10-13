package org.example.challenge3.controller;

import lombok.extern.java.Log;
import org.example.challenge3.model.Order;
import org.example.challenge3.model.Product;
import org.example.challenge3.service.ServiceOrder;
import org.example.challenge3.service.ServiceOrderImpl;
import org.example.challenge3.service.ServiceProduct;
import org.example.challenge3.service.ServiceProductImpl;
import org.example.challenge3.sql.InfoUser;
import org.example.challenge3.view.BinarFudView;

import javax.swing.*;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

public class ControllerOrder {

	BinarFudView binarFudView;
	ServiceOrder serviceOrder;


	public ControllerOrder(BinarFudView binarFudView) {
		this.binarFudView = binarFudView;
		this.serviceOrder = new ServiceOrderImpl();
	}

	public void createOrder(Product product){
		boolean isSucces = isOrderSucces(product);
		Order order = Order.builder()
				.id(UUID.randomUUID())
				.userId(InfoUser.USER_ID)
				.orderTime(LocalDate.now())
				.destinationAdress(InfoUser.DESTINATION)
				.isComplete(isSucces)
				.build();
		System.out.print("Order: ");
		System.out.println(order);
	}

	private boolean isOrderSucces(Product product) {
		if (product != null) {
			// Show an input dialog to get the quantity
			String qtyInput = JOptionPane.showInputDialog(binarFudView.getFrame(), "Enter the quantity for " + product.getProductName() + ":", "Quantity Input", JOptionPane.QUESTION_MESSAGE);

			// If the user provides a valid quantity, add a row to the table
			if (qtyInput != null && !qtyInput.isBlank()) {
				try {
					int quantity = Integer.parseInt(qtyInput);
					Object[] row = {product.getProductName(), quantity, product.getPrice() * quantity};
					binarFudView.getTableModel().addRow(row);
					return true;
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(binarFudView.getFrame(), "Invalid input. Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
					return false;
				}
			}
		}
		JOptionPane.showMessageDialog(binarFudView.getFrame(), "Oops! Your order is not complete. Please provide a valid quantity.", "Order Incomplete", JOptionPane.ERROR_MESSAGE);
		return false;
	}



}
