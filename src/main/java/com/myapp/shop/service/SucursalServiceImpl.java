package com.myapp.shop.service;

import com.myapp.shop.dto.SucursalDTO;
import com.myapp.shop.exception.SucursalNotFoundException;
import com.myapp.shop.mapper.Mapper;
import com.myapp.shop.model.Sucursal;
import com.myapp.shop.repository.SucursalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SucursalServiceImpl implements SucursalService{

    @Autowired
    private SucursalRepository repository;

    @Override
    public List<SucursalDTO> obtenerSucursales() {
        return repository.findAll()
                .stream()
                .map(Mapper::toDto)
                .toList();
    }

    @Override
    public SucursalDTO buscarSucursal(Long id) {
        Sucursal sucursal = repository.findById(id)
                .orElseThrow(() -> new SucursalNotFoundException("Sucursal no encontrada"));

        return Mapper.toDto(sucursal);
    }

    @Override
    public SucursalDTO crearSucursal(SucursalDTO sucursalDto) {
        Sucursal encontrada = repository.findByNombre(sucursalDto.getNombre());
        if (encontrada != null){
            throw new RuntimeException("La sucursal con nombre " + encontrada.getNombre() + " ya existe");
        }
        Sucursal sucursal = Sucursal.builder()
                .idSucursal(sucursalDto.getId())
                .nombre(sucursalDto.getNombre())
                .direccion(sucursalDto.getDireccion())
                .build();

        return Mapper.toDto(repository.save(sucursal));
    }

    @Override
    public SucursalDTO actualizarSucursal(Long id, SucursalDTO sucursalDto) {
        Sucursal encontrada = repository.findById(id)
                .orElseThrow(() -> new SucursalNotFoundException("Sucursal no encontrada"));
        encontrada.setNombre(sucursalDto.getNombre());
        encontrada.setDireccion(sucursalDto.getDireccion());

        return Mapper.toDto(repository.save(encontrada));
    }

    @Override
    public void eliminarSucursal(Long id) {
        if (!repository.existsById(id)){
            throw new SucursalNotFoundException("La sucursal a eliminar no se encuentra");
        }

        repository.deleteById(id);
    }
}
