package com.prameswaradev.ProductService.model.dtos;

public class ProductNotFound extends Throwable {
    public ProductNotFound(String message) {
        super(message);
    }
}
