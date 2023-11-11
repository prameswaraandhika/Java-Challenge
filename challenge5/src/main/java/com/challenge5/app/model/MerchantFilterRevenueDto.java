package com.challenge5.app.model;

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
