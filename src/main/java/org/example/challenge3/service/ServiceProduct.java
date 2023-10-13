package org.example.challenge3.service;

import org.example.challenge3.model.Product;

import java.util.List;

public interface ServiceProduct {
	public List<Product> findAll();
	default void createProduct(Product product) {
		// default method implementation
	}
	default void deleteProduct(long id) {
		// default method implementation
	}
}
