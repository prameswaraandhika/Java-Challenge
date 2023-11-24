package com.challenge6.app.model.dtos;

import com.challenge6.app.model.Merchant;
import jakarta.persistence.ManyToOne;

import java.util.UUID;

public record ProductDto(String productName, Double price) {

}
