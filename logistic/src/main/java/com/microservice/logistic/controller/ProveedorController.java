package com.microservice.logistic.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.logistic.model.Proveedor;
import com.microservice.logistic.repository.ProveedorRepository;

@RestController
@RequestMapping("/api/proveedores")

public class ProveedorController  {

    private final ProveedorRepository proveedorRepository;

    public ProveedorController(ProveedorRepository proveedorRepository) {
        this.proveedorRepository = proveedorRepository;
    }

    @PostMapping
    public Proveedor crear(@RequestBody Proveedor proveedor) {
        return proveedorRepository.save(proveedor);
    }

    @GetMapping
    public List<Proveedor> listar() {
        return proveedorRepository.findAll();
    }
}
