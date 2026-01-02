package com.myapp.shop.controller;

import com.myapp.shop.dto.ErrorDTO;
import com.myapp.shop.exception.ProductoNotFoundException;
import com.myapp.shop.exception.SucursalNotFoundException;
import com.myapp.shop.exception.VentaNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class HandlerExceptionController {

    @ExceptionHandler(ProductoNotFoundException.class)
    public ResponseEntity<ErrorDTO> productoNoEncontrado(ProductoNotFoundException ex){
        ErrorDTO error = new ErrorDTO();
        error.setError("Producto no válido!");
        error.setMensaje(ex.getMessage());
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setFecha(LocalDateTime.now());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);

    }

    @ExceptionHandler(VentaNotFoundException.class)
    public ResponseEntity<ErrorDTO> ventaNoEncontrada(VentaNotFoundException ex){
        ErrorDTO error = new ErrorDTO();
        error.setError("Venta no válida!");
        error.setMensaje(ex.getMessage());
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setFecha(LocalDateTime.now());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);

    }

    @ExceptionHandler(SucursalNotFoundException.class)
    public ResponseEntity<ErrorDTO> productoNoEncontrado(SucursalNotFoundException ex){
        ErrorDTO error = new ErrorDTO();
        error.setError("Sucursal no válida!");
        error.setMensaje(ex.getMessage());
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setFecha(LocalDateTime.now());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);

    }
}
