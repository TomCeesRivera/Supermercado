package com.myapp.shop.repository;

import com.myapp.shop.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Long> {

    Producto findByNombre(String nombre);
}
