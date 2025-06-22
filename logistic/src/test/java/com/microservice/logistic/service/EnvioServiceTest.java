package com.microservice.logistic.service;

import com.microservice.logistic.model.Envio;
import com.microservice.logistic.repository.EnvioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class EnvioServiceTest {

    private EnvioRepository envioRepository;
    private EnvioService envioService;

    @BeforeEach
    void setUp() {
        envioRepository = mock(EnvioRepository.class);
        envioService = new EnvioService(envioRepository);
    }

    @Test
    void guardarEnvio_deberiaGuardarCorrectamente() {
        Envio envio = new Envio(null, "En camino", "Viña del Mar", LocalDate.now(), "Carlos");

        when(envioRepository.save(any(Envio.class))).thenReturn(envio);

        Envio resultado = envioService.guardar(envio);

        assertNotNull(resultado);
        assertEquals("En camino", resultado.getEstado());
        verify(envioRepository).save(envio);
    }

    @Test
    void buscarPorId_deberiaRetornarEnvioCorrecto() {
        Envio envio = new Envio(1L, "Pendiente", "Santiago", LocalDate.now(), "Ana");

        when(envioRepository.findById(1L)).thenReturn(Optional.of(envio));

        Envio resultado = envioService.buscarPorId(1L);

        assertNotNull(resultado);
        assertEquals("Ana", resultado.getCliente());
    }

    @Test
    void obtenerTodos_deberiaRetornarListaEnvios() {
        List<Envio> lista = List.of(
            new Envio(1L, "Pendiente", "Valpo", LocalDate.now(), "Cliente A"),
            new Envio(2L, "En camino", "Viña", LocalDate.now(), "Cliente B")
        );

        when(envioRepository.findAll()).thenReturn(lista);

        List<Envio> resultado = envioService.obtenerTodos();

        assertEquals(2, resultado.size());
    }
}
