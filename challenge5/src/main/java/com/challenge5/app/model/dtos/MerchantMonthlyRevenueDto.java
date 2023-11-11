package com.challenge5.app.model.dtos;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MerchantMonthlyRevenueDto {
    private Date time;
    private String name;
    private String product;
    private Integer quantity;
    private Double total_price;

    public MerchantMonthlyRevenueDto(java.time.LocalDate time, String name, String product, Integer quantity, Double total_price) {
        this.time = Date.valueOf(time);
        this.name = name;
        this.product = product;
        this.quantity = quantity;
        this.total_price = total_price;
    }
}
