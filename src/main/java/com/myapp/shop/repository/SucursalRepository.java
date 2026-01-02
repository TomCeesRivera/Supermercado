package com.myapp.shop.repository;

import com.myapp.shop.model.Sucursal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SucursalRepository extends JpaRepository<Sucursal, Long> {

    Sucursal findByNombre(String nombre);
}
