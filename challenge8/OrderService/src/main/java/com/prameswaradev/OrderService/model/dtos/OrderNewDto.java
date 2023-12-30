package com.prameswaradev.OrderService.model.dtos;


import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public record OrderNewDto(
    LocalDate time,
    String address,
    boolean complete

//    List<OrderDetail> orderDetails
){}
