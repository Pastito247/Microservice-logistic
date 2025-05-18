package com.microservice.logistic.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.logistic.model.Envio;
import com.microservice.logistic.service.EnvioService;

@RestController
@RequestMapping("/api/envios")


public class EnvioController {

    private final EnvioService envioService;

    public EnvioController(EnvioService envioService) {
        this.envioService = envioService;
    }

    @GetMapping
    public List<Envio> obtenerTodos() {
        return envioService.obtenerTodos();
    }

    @PostMapping
    public Envio guardar(@RequestBody Envio envio) {
        return envioService.guardar(envio);
    }

    @GetMapping("/{id}")
    public Envio buscarPorId(@PathVariable Long id) {
        return envioService.buscarPorId(id);
    }
    
}
