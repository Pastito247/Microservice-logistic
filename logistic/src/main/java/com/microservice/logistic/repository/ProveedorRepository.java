package com.microservice.logistic.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservice.logistic.model.Proveedor;


public interface ProveedorRepository extends JpaRepository<Proveedor, Long> {
    Optional<Proveedor> findByProductoSuministrado(String productoSuministrado);
}