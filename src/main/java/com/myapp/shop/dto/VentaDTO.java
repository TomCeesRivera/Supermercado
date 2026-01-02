package com.myapp.shop.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VentaDTO {
    private Long id;
    private LocalDate fecha;
    private Long idSucursal;
    private List<DetalleVentaDTO> detalles;
    private Double total;

}
