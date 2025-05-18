package com.microservice.logistic.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.logistic.service.ProveedorService;

@RestController
@RequestMapping("/api/stock")

public class StockController {

    private final ProveedorService proveedorService;

    public StockController(ProveedorService proveedorService) {
        this.proveedorService = proveedorService;
    }

    @PostMapping("/verificar")
    public String verificarStock(
            @RequestParam String producto,
            @RequestParam int stock
    ) {
        proveedorService.pedirProducto(producto, stock);
        return "Verificación completada";
    }
}
