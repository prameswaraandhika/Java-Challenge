package com.challenge4.demo.controller;

import com.challenge4.demo.model.Product;
import com.challenge4.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.UUID;

@Component
public class ProductController {
	@Autowired
	ProductService productService;

	public List<Product> getAllProducts(){
		return productService.findAll();
	}

	public void create(Product product) {
		productService.create(product);
	}

	public void update(UUID id, String name) {
		productService.update(id, name);
	}

	public void delete(UUID id) {
		productService.delete(id);
	}
}
