package com.challenge4.demo.controller;

import com.challenge4.demo.model.Product;
import com.challenge4.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class ProductController {

	ProductService serviceProduct;

	@Autowired
	public ProductController(ProductService serviceProduct) {
		this.serviceProduct = serviceProduct;
	}



	public List<Product> getAllProducts(){
		return serviceProduct.findAll();
	}


}
