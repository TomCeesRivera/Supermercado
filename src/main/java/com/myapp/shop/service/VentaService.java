package com.myapp.shop.service;

import com.myapp.shop.dto.VentaDTO;
import com.myapp.shop.model.Venta;

import java.time.LocalDate;
import java.util.List;

public interface VentaService {

    List<VentaDTO> listarPorSucursalYFecha(Long id, LocalDate fecha);

    VentaDTO buscarVenta(Long id);

    VentaDTO crearVenta(VentaDTO ventaDto);

    void eliminarVenta(Long id);
}
