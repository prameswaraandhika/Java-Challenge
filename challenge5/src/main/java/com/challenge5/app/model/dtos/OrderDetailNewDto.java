package com.challenge5.app.model.dtos;

import com.challenge5.app.model.OrderDetail;
import com.challenge5.app.model.Product;

import java.util.Set;
import java.util.UUID;

public record OrderDetailNewDto(
         Integer quantity,
         Double totalPrice,
         UUID idProduct


) {
}
