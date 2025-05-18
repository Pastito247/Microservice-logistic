package com.microservice.logistic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservice.logistic.model.Envio;

public interface EnvioRepository extends JpaRepository<Envio, Long> {

}
