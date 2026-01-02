package com.myapp.shop.controller;

import com.myapp.shop.dto.ProductoDTO;
import com.myapp.shop.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping
    public ResponseEntity<List<ProductoDTO>> obtnerProductos(){
        return ResponseEntity.ok(productoService.obtenerProductos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductoDTO> buscarProducto(@PathVariable Long id){
        return ResponseEntity.ok(productoService.buscarProducto(id));
    }

    @PostMapping
    public ResponseEntity<ProductoDTO> crearProducto(@RequestBody ProductoDTO productoDto){
        ProductoDTO creado = productoService.crearProducto(productoDto);

        return ResponseEntity
                .created(URI.create("/api/productos/" + creado.getId()))
                .body(creado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductoDTO> actualizarProducto(
            @PathVariable Long id,
            @RequestBody ProductoDTO productoDto
    ){
        return ResponseEntity.ok(productoService.actualizarProducto(id,productoDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long id){
        return ResponseEntity.noContent().build();
    }
}
