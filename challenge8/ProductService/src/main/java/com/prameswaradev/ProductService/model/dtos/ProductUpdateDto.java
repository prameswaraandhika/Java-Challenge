package com.prameswaradev.ProductService.model.dtos;

import java.util.UUID;

public record ProductUpdateDto(UUID idProduct, String name, Double price) {

}
