package com.myapp.shop.service;

import com.myapp.shop.dto.ProductoDTO;
import com.myapp.shop.exception.ProductoNotFoundException;
import com.myapp.shop.mapper.Mapper;
import com.myapp.shop.model.Producto;
import com.myapp.shop.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoServiceImpl implements ProductoService{

    @Autowired
    private ProductoRepository repository;

    @Override
    public List<ProductoDTO> obtenerProductos() {
        return repository.findAll().stream()
                .map(Mapper::toDto)
                .toList();
    }

    @Override
    public ProductoDTO buscarProducto(Long id) {
        Producto producto = repository.findById(id)
                .orElseThrow(() -> new ProductoNotFoundException("Producto no encontrado"));

        return Mapper.toDto(producto);

    }

    @Override
    public ProductoDTO crearProducto(ProductoDTO productoDto) {
        if(productoDto == null){
            throw new IllegalArgumentException("El producto no puede ser null");
        }

        Producto encontrado = repository.findByNombre(productoDto.getNombre());
        if (encontrado != null){
            throw new IllegalStateException("El producto con nombre " + encontrado.getNombre() + " ya existe");
        }

        Producto producto = Producto.builder()
                .nombre(productoDto.getNombre())
                .descripcion(productoDto.getDescripcion())
                .precio(productoDto.getPrecio())
                .build();

        return Mapper.toDto(repository.save(producto));
    }

    @Override
    public ProductoDTO actualizarProducto(Long id, ProductoDTO productoDto) {
        if (productoDto == null) {
            throw new IllegalArgumentException("El producto no puede ser null");
        }

        Producto encontrado = repository.findById(id)
                .orElseThrow(() -> new ProductoNotFoundException("Producto no encontrado"));

        Producto existente = repository.findByNombre(productoDto.getNombre());
        if(existente != null && !existente.getIdProducto().equals(id)){
            throw new IllegalStateException("Ya existe un producto con ese nombre");
        }

        if(productoDto.getPrecio() <= 0){
            throw new IllegalArgumentException("El precio debe ser mayor a 0");
        }

        encontrado.setNombre(productoDto.getNombre());
        encontrado.setDescripcion(productoDto.getDescripcion());
        encontrado.setPrecio(productoDto.getPrecio());

        return Mapper.toDto(repository.save(encontrado));
    }

    @Override
    public void eliminarProducto(Long id) {
        if (!repository.existsById(id)){
            throw new ProductoNotFoundException("El producto a eliminar no se encuentra");
        }

        repository.deleteById(id);
    }
}
