package com.challenge4.demo.service;


import com.challenge4.demo.model.Product;

import java.util.List;
import java.util.UUID;

public interface ProductService {
	public List<Product> findAll();
	void create(Product product);
	void delete(UUID id);
	void update(UUID id, String name);
}
