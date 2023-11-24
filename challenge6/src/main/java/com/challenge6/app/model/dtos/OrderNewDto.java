package com.challenge6.app.model.dtos;

import com.challenge6.app.model.OrderDetail;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public record OrderNewDto(
    LocalDate time,
    String adress,
    boolean complete,

    List<OrderDetail> orderDetails
){}
