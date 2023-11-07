package com.challenge5.app.model.dtos;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.UUID;

public record MerchantCreateDto(String merchantName, String merchantLocation) {

}
