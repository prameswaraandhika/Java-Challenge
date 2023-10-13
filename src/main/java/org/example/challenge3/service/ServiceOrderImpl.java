package org.example.challenge3.service;

import org.example.challenge3.model.Order;
import org.example.challenge3.model.OrderDetail;
import org.example.challenge3.sql.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class ServiceOrderImpl implements ServiceOrder{

	@Override
	public void createOrder(Order order) {
		String insertSql = "INSERT INTO public.orders (id, order_time, destination_address, user_id, complete) " +
				"VALUES (?, ?, ?, ?, ?)";

		try (Connection connection = ConnectionManager.getConnection("binarfud");
		     PreparedStatement preparedStatement = connection.prepareStatement(insertSql)) {

			preparedStatement.setObject(1, order.getId());
			preparedStatement.setObject(2, order.getOrderTime());
			preparedStatement.setString(3, order.getDestinationAdress());
			preparedStatement.setObject(4, order.getUserId());
			preparedStatement.setBoolean(5, order.isComplete());
			preparedStatement.executeUpdate();
			System.out.println("Order inserted successfully.");
			OrderDetail orderDetail = new OrderDetail();
			createOrderDetail(orderDetail);

		} catch (SQLException e) {
			throw new RuntimeException("Failed to create order in the database.", e);
		}
	}

	private void createOrderDetail(OrderDetail orderDetail) {
		String insertSql = "INSERT INTO public.order_details (id, order_id, product_id, quantity) VALUES (?, ?, ?, ?)";

		try (Connection connection = ConnectionManager.getConnection("binarfud");
		     PreparedStatement preparedStatement = connection.prepareStatement(insertSql)) {

			preparedStatement.setObject(1, orderDetail.getId());
			preparedStatement.setObject(2, orderDetail.getOrderId());
			preparedStatement.setObject(3, orderDetail.getProductId());
			preparedStatement.setInt(4, orderDetail.getQuantity());

			preparedStatement.executeUpdate();
			System.out.println("Order detail inserted successfully.");

		} catch (SQLException e) {
			throw new RuntimeException("Failed to create order detail in the database.", e);
		}
	}

	@Override
	public double getTotalPriceOfOrder() {
		String query = "";
		return 0;
	}
}
