package com.microservice.logistic.controller;

import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.microservice.logistic.service.ProveedorService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/stock")
public class StockController {

    private final ProveedorService proveedorService;

    public StockController(ProveedorService proveedorService) {
        this.proveedorService = proveedorService;
    }

    @Operation(summary = "Verificar stock de un producto", description = "Solicita el pedido automático de un producto si el stock es bajo")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Verificación y pedido completados exitosamente"),
        @ApiResponse(responseCode = "400", description = "Parámetros inválidos")
    })
    @PostMapping("/verificar")
    public ResponseEntity<EntityModel<String>> verificarStock(
            @Parameter(description = "Nombre del producto a verificar", example = "Perfume A")
            @RequestParam String producto,
            
            @Parameter(description = "Cantidad mínima de stock requerida", example = "5")
            @RequestParam int stock
    ) {
        proveedorService.pedirProducto(producto, stock);
        EntityModel<String> recurso = EntityModel.of("Verificación completada",
            linkTo(methodOn(StockController.class).verificarStock(producto, stock)).withSelfRel());
        return ResponseEntity.ok(recurso);
    }
}
