package com.challenge5.app.model.dtos;

import com.challenge5.app.model.Merchant;
import jakarta.persistence.ManyToOne;

import java.util.UUID;

public record ProductDto(String productName, Double price) {

}
