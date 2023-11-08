package com.challenge5.app.model.dtos;

import java.util.UUID;

public record ProductUpdateDto (UUID idProduct, String productName, Double price) {

}
