package com.myapp.shop.controller;

import com.myapp.shop.dto.VentaDTO;
import com.myapp.shop.service.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/ventas")
public class VentaController {

    @Autowired
    private VentaService service;

    @GetMapping
    public ResponseEntity<List<VentaDTO>> listarPorSucursalYFecha(
            @RequestParam Long id,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fecha
            )
    {
        return ResponseEntity.ok(service.listarPorSucursalYFecha(id, fecha));
    }

    @GetMapping("/{id}")
    public ResponseEntity<VentaDTO> buscarVenta(@PathVariable Long id){
        return ResponseEntity.ok(service.buscarVenta(id));
    }

    @PostMapping
    public ResponseEntity<VentaDTO> registrarVenta(@RequestBody VentaDTO ventaDto){
        VentaDTO creada = service.crearVenta(ventaDto);
        return ResponseEntity
                .created(URI.create("/api/ventas/" + creada.getId()))
                .body(creada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarVenta(@PathVariable Long id){
        service.eliminarVenta(id);

        return ResponseEntity.noContent().build();
    }
}
