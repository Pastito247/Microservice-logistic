package com.microservice.logistic;

import java.time.LocalDate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.microservice.logistic.model.Envio;
import com.microservice.logistic.model.Proveedor;
import com.microservice.logistic.repository.EnvioRepository;
import com.microservice.logistic.repository.ProveedorRepository;

@Component

public class DataLoader implements CommandLineRunner {

    private final EnvioRepository envioRepository;
    private final ProveedorRepository proveedorRepository;

    public DataLoader(EnvioRepository envioRepository, ProveedorRepository proveedorRepository) {
        this.envioRepository = envioRepository;
        this.proveedorRepository = proveedorRepository;
    }

    @Override
    public void run(String... args) {
        if (envioRepository.count() == 0) {
            envioRepository.save(new Envio(null, "Pendiente", "Valparaíso", LocalDate.now(), "Juan Pérez"));
            envioRepository.save(new Envio(null, "En camino", "Santiago", LocalDate.now(), "Ana Torres"));
            envioRepository.save(new Envio(null, "Entregado", "Rancagua", LocalDate.now(), "Luis Gómez"));
        }

        if (proveedorRepository.count() == 0) {
            proveedorRepository.save(new Proveedor(null, "Distribuidora Central", "central@proveedores.com", "Perfume"));
            proveedorRepository.save(new Proveedor(null, "Logística Rápida", "contacto@rapida.com", "Cajas"));
        }
    }

}
