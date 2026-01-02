package com.myapp.shop.service;

import com.myapp.shop.dto.DetalleVentaDTO;
import com.myapp.shop.dto.VentaDTO;
import com.myapp.shop.exception.ProductoNotFoundException;
import com.myapp.shop.exception.SucursalNotFoundException;
import com.myapp.shop.exception.VentaNotFoundException;
import com.myapp.shop.mapper.Mapper;
import com.myapp.shop.model.DetalleVenta;
import com.myapp.shop.model.Producto;
import com.myapp.shop.model.Sucursal;
import com.myapp.shop.model.Venta;
import com.myapp.shop.repository.ProductoRepository;
import com.myapp.shop.repository.SucursalRepository;
import com.myapp.shop.repository.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class VentaServiceImpl implements VentaService {

    @Autowired
    private VentaRepository ventaRepository;
    @Autowired
    private SucursalRepository sucursalRepository;
    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public List<VentaDTO> listarPorSucursalYFecha(Long id, LocalDate fecha) {
        boolean existe = sucursalRepository.existsById(id);
        if (!existe){
            throw new SucursalNotFoundException("No se encuentra la sucursal solicitada");
        }

        return ventaRepository.findBySucursalIdSucursalAndFecha(id, fecha)
                .stream()
                .map(Mapper::toDto)
                .toList();
    }

    @Override
    public VentaDTO buscarVenta(Long id) {
        Venta venta = ventaRepository.findById(id)
                .orElseThrow(() -> new VentaNotFoundException("Venta no encontrada"));

        return Mapper.toDto(venta);
    }

    @Override
    public VentaDTO crearVenta(VentaDTO ventaDto) {
      // LocalDate fecha = venta.getFecha();
        Venta venta = new Venta();
        venta.setFecha(ventaDto.getFecha());
        Sucursal sucursal = sucursalRepository.findById(ventaDto.getIdSucursal())
                        .orElseThrow(() -> new SucursalNotFoundException("Sucursal no encontrada"));
        venta.setSucursal(sucursal);
        double total = 0.0;

        List<DetalleVenta> detalles = new ArrayList<>();
        for(DetalleVentaDTO d: ventaDto.getDetalles()){
            Producto producto = productoRepository.findById(d.getIdProducto())
                    .orElseThrow(() -> new ProductoNotFoundException("Producto no encontrado"));
            DetalleVenta detalle = new DetalleVenta();
            detalle.setVenta(venta);
            detalle.setProducto(producto);
            detalle.setCantidad(d.getCantidad());

            detalles.add(detalle);
        }

        venta.setDetalles(detalles);

        total = detalles.stream()
                .mapToDouble(d -> d.getProducto().getPrecio() * d.getCantidad())
                .sum();

        venta.setTotal(total);

        return Mapper.toDto(ventaRepository.save(venta));
    }

    @Override
    public void eliminarVenta(Long id) {
        if (!ventaRepository.existsById(id)){
            throw new VentaNotFoundException("La venta a eliminar no se encuentra");
        }

        ventaRepository.deleteById(id);
    }
}
