package com.myapp.shop.exception;

public class SucursalNotFoundException extends RuntimeException {
    public SucursalNotFoundException(String message) {
        super(message);
    }
}
