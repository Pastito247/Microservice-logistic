package com.microservice.logistic.controller;

import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.microservice.logistic.service.RutaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/rutas")
public class RutaController {

    private final RutaService rutaService;

    public RutaController(RutaService rutaService) {
        this.rutaService = rutaService;
    }

    @Operation(summary = "Calcular ruta entre origen y destino", description = "Devuelve la ruta optimizada entre dos puntos")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Ruta calculada correctamente"),
        @ApiResponse(responseCode = "400", description = "Parámetros inválidos")
    })
    @GetMapping
    public ResponseEntity<EntityModel<String>> obtenerRuta(
            @Parameter(description = "Ciudad origen", example = "Viña del Mar")
            @RequestParam String origen,
            
            @Parameter(description = "Ciudad destino", example = "Santiago")
            @RequestParam String destino
    ) {
        String ruta = rutaService.calcularRuta(origen, destino);
        EntityModel<String> recurso = EntityModel.of(ruta,
            linkTo(methodOn(RutaController.class).obtenerRuta(origen, destino)).withSelfRel());
        return ResponseEntity.ok(recurso);
    }
}
