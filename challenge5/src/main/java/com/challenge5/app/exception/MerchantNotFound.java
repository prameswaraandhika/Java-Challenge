package com.challenge5.app.exception;

public class MerchantNotFound extends RuntimeException {
    public MerchantNotFound(String message) {
        super(message);
    }
}
