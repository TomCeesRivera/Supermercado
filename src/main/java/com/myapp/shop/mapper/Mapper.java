package com.myapp.shop.mapper;

import com.myapp.shop.dto.DetalleVentaDTO;
import com.myapp.shop.dto.ProductoDTO;
import com.myapp.shop.dto.SucursalDTO;
import com.myapp.shop.dto.VentaDTO;
import com.myapp.shop.model.DetalleVenta;
import com.myapp.shop.model.Producto;
import com.myapp.shop.model.Sucursal;
import com.myapp.shop.model.Venta;

import java.util.ArrayList;
import java.util.List;

public class Mapper {

    public static SucursalDTO toDto(Sucursal sucursal){
        if(sucursal == null) return null;

        return SucursalDTO.builder()
                .id(sucursal.getIdSucursal())
                .nombre(sucursal.getNombre())
                .direccion(sucursal.getDireccion())
                .build();
    }

    public static DetalleVentaDTO toDto(DetalleVenta detalle){
        if(detalle == null) return null;

        Double subtotal = detalle.getProducto().getPrecio() * detalle.getCantidad();

        return DetalleVentaDTO.builder()
                .id(detalle.getIdDetalle())
                .idProducto(detalle.getProducto().getIdProducto())
                .nombreProducto(detalle.getProducto().getNombre())
                .precio(detalle.getProducto().getPrecio())
                .cantidad(detalle.getCantidad())
                .subtotal(subtotal)
                .build();
    }

    public static VentaDTO toDto(Venta venta){
        if(venta == null) return null;

        List<DetalleVentaDTO> detalles = new ArrayList<>();

        detalles = venta.getDetalles().stream()
                .map(Mapper::toDto)
                .toList();

        return VentaDTO.builder()
                .id(venta.getIdVenta())
                .fecha(venta.getFecha())
                .idSucursal(venta.getSucursal().getIdSucursal())
                .detalles(detalles)
                .total(venta.getTotal())
                .build();
    }

    public static ProductoDTO toDto(Producto producto){
        if(producto == null) return null;

        return ProductoDTO.builder()
                .id(producto.getIdProducto())
                .nombre(producto.getNombre())
                .descripcion(producto.getDescripcion())
                .precio(producto.getPrecio())
                .build();
    }


}
