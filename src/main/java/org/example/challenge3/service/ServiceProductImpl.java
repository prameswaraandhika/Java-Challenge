package org.example.challenge3.service;

import org.example.challenge3.model.Product;
import org.example.challenge3.sql.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ServiceProductImpl implements ServiceProduct{
	@Override
	public List<Product> findAll() {
		String query = "SELECT id, product_name, price, merchant_id FROM public.products";
		List<Product> products = new ArrayList<>();

		try (Connection connection = ConnectionManager.getConnection("binarfud");
		     PreparedStatement preparedStatement = connection.prepareStatement(query);
		     ResultSet resultSet = preparedStatement.executeQuery()) {
			while (resultSet.next()) {
				UUID id = UUID.fromString(resultSet.getString("id"));
				String productName = resultSet.getString("product_name");
				double price = resultSet.getDouble("price");
				UUID merchantId = UUID.fromString(resultSet.getString("merchant_id"));

				Product product = new Product(id, productName, price, merchantId);
				products.add(product);
			}
		} catch (SQLException e) {
			throw new RuntimeException("Failed to retrieve products from the database.", e);
		}
		return products;
	}

}
