package org.example.challenge3.controller;

import org.example.challenge3.model.Product;
import org.example.challenge3.service.ServiceProduct;
import org.example.challenge3.service.ServiceProductImpl;
import org.example.challenge3.view.BinarFudView;

import java.util.List;

public class ControllerProduct {

	BinarFudView binarFudView;
	ServiceProduct serviceProduct;

	public ControllerProduct(BinarFudView binarFudView) {
		this.binarFudView = binarFudView;
		this.serviceProduct = new ServiceProductImpl();
	}

	public List<Product> getAllProducts(){
		return serviceProduct.findAll();
	}


}
