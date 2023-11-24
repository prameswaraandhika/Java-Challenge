package com.challenge6.app.model.dtos;

import com.challenge6.app.model.OrderDetail;
import com.challenge6.app.model.Product;

import java.util.Set;
import java.util.UUID;

public record OrderDetailNewDto(
         Integer quantity,
         Double totalPrice,
         UUID idProduct


) {
}
