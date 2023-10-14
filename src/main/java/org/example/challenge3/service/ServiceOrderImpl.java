package org.example.challenge3.service;

import org.example.challenge3.model.Order;
import org.example.challenge3.model.OrderDetail;
import org.example.challenge3.sql.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class ServiceOrderImpl implements ServiceOrder {

	@Override
	public void createOrder(Order order) {
		String query = "INSERT INTO public.orders(\n" +
				"\tid, order_time, destination_address, user_id, is_complete)\n" +
				"\tVALUES (?, ?, ?, ?, ?);";

		try (Connection connection = ConnectionManager.getConnection("binarfud");
		     PreparedStatement preparedStatement = connection.prepareStatement(query)) {

			preparedStatement.setObject(1, order.getId());
			preparedStatement.setObject(2, order.getOrderTime());
			preparedStatement.setString(3, order.getDestinationAdress());
			preparedStatement.setObject(4, order.getUserId());
			preparedStatement.setBoolean(5, order.isComplete());
			preparedStatement.executeUpdate();
			System.out.println("Order inserted successfully.");


		} catch (SQLException e) {
			throw new RuntimeException("Failed to create order in the database.", e);
		}
	}

	public void createOrderDetail(OrderDetail orderDetail) {
		String query = "INSERT INTO public.order_details(\n" +
				"\tid, order_id, product_id, quantity, total_price)\n" +
				"\tVALUES (?, ?, ?, ?, ?);";

		try (Connection connection = ConnectionManager.getConnection("binarfud");
		     PreparedStatement preparedStatement = connection.prepareStatement(query)) {

			preparedStatement.setObject(1, orderDetail.getId());
			preparedStatement.setObject(2, orderDetail.getOrderId());
			preparedStatement.setObject(3, orderDetail.getProductId());
			preparedStatement.setInt(4, orderDetail.getQuantity());
			preparedStatement.setDouble(5, orderDetail.getTotalPrice());


			preparedStatement.executeUpdate();
			System.out.println("Order detail inserted successfully.");

		} catch (SQLException e) {
			throw new RuntimeException("Failed to create order detail in the database.", e);
		}
	}

	@Override
	public Double getTotalPriceOfOrder() {
		String query = "SELECT SUM(total_price) AS total_price\n" +
				"FROM public.order_details;";
		Double totalPrice = null;

		try (Connection connection = ConnectionManager.getConnection("binarfud");
		     PreparedStatement preparedStatement = connection.prepareStatement(query);
		     ResultSet resultSet = preparedStatement.executeQuery()) {

			if (resultSet.next()) {
				totalPrice = resultSet.getDouble("total_price");
			}

		} catch (SQLException e) {
			throw new RuntimeException("Failed to retrieve total order price from the database.", e);
		}

		return totalPrice;
	}

	@Override
	public Order getOrderByUserId(UUID userId) {
		String query = "SELECT * FROM orders WHERE user_id = ?";

		try (Connection connection = ConnectionManager.getConnection("binarfud");
		     PreparedStatement preparedStatement = connection.prepareStatement(query)) {

			preparedStatement.setObject(1, userId);
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				if (resultSet.next()) {
					Order order = new Order();
					order.setId((UUID) resultSet.getObject("id"));
					order.setUserId((UUID) resultSet.getObject("user_id"));
					order.setDestinationAdress(resultSet.getString("destination_address"));
					order.setComplete(resultSet.getBoolean("is_complete"));
					return order;
				}
				System.out.println("Order detail inserted successfully.");

			} catch (SQLException e) {
				throw new RuntimeException("Failed to create order detail in the database.", e);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return null;
	}

	@Override
	public OrderDetail getOrderDetailById(UUID orderDetailId) {
		String query = "SELECT * FROM public.order_details WHERE id = ?";

		try (Connection connection = ConnectionManager.getConnection("binarfud");
		     PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setObject(1, orderDetailId);

			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				if (resultSet.next()) {
					OrderDetail orderDetail = new OrderDetail();
					orderDetail.setId((UUID) resultSet.getObject("id"));
					orderDetail.setOrderId((UUID) resultSet.getObject("order_id"));
					orderDetail.setProductId((UUID) resultSet.getObject("product_id"));
					orderDetail.setQuantity(resultSet.getInt("quantity"));
					orderDetail.setTotalPrice(resultSet.getDouble("total_price"));
					return orderDetail;
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException("Failed to retrieve order detail from the database.", e);
		}
		return null; // Return null if order detail is not found
	}


}
