package com.microservice.logistic.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.microservice.logistic.model.Envio;
import com.microservice.logistic.service.EnvioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/envios")
public class EnvioController {

    private final EnvioService envioService;

    public EnvioController(EnvioService envioService) {
        this.envioService = envioService;
    }

    @Operation(summary = "Obtiene todos los envíos", description = "Retorna una lista con todos los envíos registrados")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista obtenida correctamente")
    })
    @GetMapping
    public CollectionModel<EntityModel<Envio>> obtenerTodos() {
        List<EntityModel<Envio>> envios = envioService.obtenerTodos().stream()
            .map(envio -> EntityModel.of(envio,
                linkTo(methodOn(EnvioController.class).buscarPorId(envio.getId())).withSelfRel(),
                linkTo(methodOn(EnvioController.class).obtenerTodos()).withRel("envios")))
            .collect(Collectors.toList());

        return CollectionModel.of(envios,
            linkTo(methodOn(EnvioController.class).obtenerTodos()).withSelfRel());
    }

    @Operation(summary = "Guarda un nuevo envío", description = "Crea un nuevo envío con la información proporcionada")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Envío creado correctamente"),
        @ApiResponse(responseCode = "400", description = "Datos de envío inválidos")
    })
    @PostMapping
    public ResponseEntity<EntityModel<Envio>> guardar(@RequestBody Envio envio) {
        Envio nuevoEnvio = envioService.guardar(envio);
        EntityModel<Envio> recurso = EntityModel.of(nuevoEnvio,
            linkTo(methodOn(EnvioController.class).buscarPorId(nuevoEnvio.getId())).withSelfRel(),
            linkTo(methodOn(EnvioController.class).obtenerTodos()).withRel("envios"));

        return ResponseEntity
            .created(recurso.getRequiredLink(IanaLinkRelations.SELF).toUri())
            .body(recurso);
    }

    @Operation(summary = "Busca un envío por ID", description = "Retorna un envío específico según su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Envío encontrado"),
        @ApiResponse(responseCode = "404", description = "Envío no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Envio>> buscarPorId(@PathVariable Long id) {
        Envio envio = envioService.buscarPorId(id);
        if (envio == null) {
            return ResponseEntity.notFound().build();
        }
        EntityModel<Envio> recurso = EntityModel.of(envio,
            linkTo(methodOn(EnvioController.class).buscarPorId(id)).withSelfRel(),
            linkTo(methodOn(EnvioController.class).obtenerTodos()).withRel("envios"));
        return ResponseEntity.ok(recurso);
    }
}
