package com.microservice.logistic.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.microservice.logistic.model.Envio;
import com.microservice.logistic.repository.EnvioRepository;

@Service

public class EnvioService {

    private final EnvioRepository envioRepository;

    public EnvioService(EnvioRepository envioRepository) {
        this.envioRepository = envioRepository;
    }

    public List<Envio> obtenerTodos(){
        return envioRepository.findAll();
    }

    public Envio guardar(Envio envio){
        return envioRepository.save(envio);
    }

    public Envio buscarPorId(Long id){
        return envioRepository.findById(id).orElse(null);
    }

    
}
