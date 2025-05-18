package com.microservice.logistic.controller;

import org.springframework.web.bind.annotation.*;
import com.microservice.logistic.service.RutaService;

@RestController
@RequestMapping("/api/rutas")
public class RutaController {

    private final RutaService rutaService;

     public RutaController(RutaService rutaService) {
        this.rutaService = rutaService;
    }

    @GetMapping
    public String obtenerRuta(
            @RequestParam String origen,
            @RequestParam String destino
    ) {
        return rutaService.calcularRuta(origen, destino);
    }
}
