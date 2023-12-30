package com.prameswaradev.ProductService.model.dtos;


import java.time.LocalDateTime;

public record ExceptionResponse(LocalDateTime timeStamp, String message, String details) {

}
