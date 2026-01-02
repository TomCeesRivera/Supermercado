package com.myapp.shop.exception;

public class VentaNotFoundException extends RuntimeException {
    public VentaNotFoundException(String message) {
        super(message);
    }
}
