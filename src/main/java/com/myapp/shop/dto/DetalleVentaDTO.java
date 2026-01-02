package com.myapp.shop.dto;

import lombok.*;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DetalleVentaDTO {
    private Long id;
    private Long idProducto;
    private String nombreProducto;
    private Double precio;
    private int cantidad;
    private Double subtotal;
}
