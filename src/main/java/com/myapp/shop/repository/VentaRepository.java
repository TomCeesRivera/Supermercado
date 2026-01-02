package com.myapp.shop.repository;

import com.myapp.shop.model.Venta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface VentaRepository extends JpaRepository<Venta, Long> {

    List<Venta> findBySucursalIdSucursalAndFecha(Long id, LocalDate fecha);
}
