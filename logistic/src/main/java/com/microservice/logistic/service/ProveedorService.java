package com.microservice.logistic.service;

import org.springframework.stereotype.Service;
import com.microservice.logistic.model.Proveedor;
import com.microservice.logistic.repository.ProveedorRepository;

@Service
public class ProveedorService {

    private final ProveedorRepository proveedorRepository;

    public ProveedorService(ProveedorRepository proveedorRepository) {
        this.proveedorRepository = proveedorRepository;
    }

    public void pedirProducto(String producto, int stockActual) {
        if (stockActual < 10) {
            proveedorRepository.findByProductoSuministrado(producto).ifPresent(proveedor -> {
                System.out.println("📦 Enviando pedido automático a: " + proveedor.getNombre());
                System.out.println("Producto: " + producto);
                System.out.println("Contacto: " + proveedor.getContacto());
            });
        }
    }
}
