package com.challenge4.demo.service;


import com.challenge4.demo.model.Product;

import java.util.List;

public interface ProductService {
	public List<Product> findAll();
	default void createProduct(Product product) {
		// default method implementation
	}
	default void deleteProduct(long id) {
		// default method implementation
	}
}
