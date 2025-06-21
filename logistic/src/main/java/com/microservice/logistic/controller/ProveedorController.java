package com.microservice.logistic.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.microservice.logistic.model.Proveedor;
import com.microservice.logistic.repository.ProveedorRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/proveedores")
public class ProveedorController  {

    private final ProveedorRepository proveedorRepository;

    public ProveedorController(ProveedorRepository proveedorRepository) {
        this.proveedorRepository = proveedorRepository;
    }

    @Operation(summary = "Crear nuevo proveedor", description = "Registra un nuevo proveedor en el sistema")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Proveedor creado correctamente"),
        @ApiResponse(responseCode = "400", description = "Datos inválidos para el proveedor")
    })
    @PostMapping
    public ResponseEntity<EntityModel<Proveedor>> crear(@RequestBody Proveedor proveedor) {
        Proveedor nuevoProveedor = proveedorRepository.save(proveedor);
        EntityModel<Proveedor> recurso = EntityModel.of(nuevoProveedor,
            linkTo(methodOn(ProveedorController.class).crear(nuevoProveedor)).withSelfRel(),
            linkTo(methodOn(ProveedorController.class).listar()).withRel("proveedores"));

        return ResponseEntity
            .created(recurso.getRequiredLink(IanaLinkRelations.SELF).toUri())
            .body(recurso);
    }

    @Operation(summary = "Listar proveedores", description = "Obtiene la lista completa de proveedores registrados")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de proveedores obtenida correctamente")
    })
    @GetMapping
    public CollectionModel<EntityModel<Proveedor>> listar() {
        List<EntityModel<Proveedor>> proveedores = proveedorRepository.findAll().stream()
            .map(proveedor -> EntityModel.of(proveedor,
                linkTo(methodOn(ProveedorController.class).crear(proveedor)).withSelfRel(),
                linkTo(methodOn(ProveedorController.class).listar()).withRel("proveedores")))
            .collect(Collectors.toList());

        return CollectionModel.of(proveedores,
            linkTo(methodOn(ProveedorController.class).listar()).withSelfRel());
    }
}
