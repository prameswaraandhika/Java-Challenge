package com.challenge6.app.model.dtos;

import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MerchantFilterRevenueDto {
    private LocalDate start;
    private LocalDate end;
}
