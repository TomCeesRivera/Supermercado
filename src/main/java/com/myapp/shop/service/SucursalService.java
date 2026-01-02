package com.myapp.shop.service;

import com.myapp.shop.dto.SucursalDTO;

import java.util.List;

public interface SucursalService {

    List<SucursalDTO> obtenerSucursales();

    SucursalDTO buscarSucursal(Long id);

    SucursalDTO crearSucursal(SucursalDTO sucursal);

    SucursalDTO actualizarSucursal(Long id, SucursalDTO sucursal);

    void eliminarSucursal(Long id);
}
