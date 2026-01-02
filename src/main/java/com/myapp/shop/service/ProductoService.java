package com.myapp.shop.service;

import com.myapp.shop.dto.ProductoDTO;
import com.myapp.shop.model.Producto;

import java.util.List;

public interface ProductoService {

    List<ProductoDTO> obtenerProductos();

    ProductoDTO buscarProducto(Long id);

    ProductoDTO crearProducto(ProductoDTO producto);

    ProductoDTO actualizarProducto(Long id, ProductoDTO producto);

    void eliminarProducto(Long id);
}
