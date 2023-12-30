package com.prameswaradev.OrderService.model.dtos;

import java.util.UUID;

public record OrderDetailNewDto(
        UUID idProduct,
        Integer quantity,
        Double totalPrice
) {
}
