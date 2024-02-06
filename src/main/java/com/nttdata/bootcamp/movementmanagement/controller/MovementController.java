package com.nttdata.bootcamp.movementmanagement.controller;

import com.nttdata.bootcamp.movementmanagement.model.Movement;
import com.nttdata.bootcamp.movementmanagement.service.MovementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Clase controlodaro para la administración de movimientos del sistema.
 */
@RestController
@RequestMapping("/movement")
public class MovementController {
    /**
     * Interface del servicio de movimientos.
     */
    @Autowired
    private MovementService movementService;

    /**
     * Metodo encargado de buscar y retornar todos los movimientos del sistema.
     * @return Retorno de todos los movimientos realizados.
     */
    @GetMapping()
    public Flux<Movement> findMovements() {
        return movementService.findMovements();
    }

    /**
     * Método encargado de buscar y retornar un movimiento en específico.
     * @param id Id del movimiento especifico a retornar.
     * @return Retorno de un movimiento especifico.
     */
    @GetMapping("/{id}")
    public Mono<Movement> findById(@PathVariable final String id) {
        return movementService.findById(id);
    }

    /**
     * Método encargado de buscar y retornar movimientos por un id de cliente.
     * @param clientId Id del cliente del cual buscar sus movimientos
     * @return Retorna todos los movimientos de un cliente
     */
    @GetMapping("/client/{clientId}")
    public Flux<Movement> findMovementsByClientId(@PathVariable final String clientId) {
        return movementService.findByClientId(clientId);
    }

    /**
     * Método encargado de buscar y retornar movimientos por un id de producto.
     * @param productId Id del producto del cual buscar sus movimientos
     * @return Retorna todos los movimientos de un producto
     */
    @GetMapping("/product/{productId}")
    public Flux<Movement> findMovementsByProductId(@PathVariable final String productId) {
        return movementService.findByProductId(productId);
    }

    /**
     * Método encargado de crear movimientos transaccionales.
     * @param movement Cuerpo a crear de un movimiento
     * @return Retorno del cuerpo del movimiento creado
     */
    @PostMapping()
    public Mono<Movement> createMovement(@RequestBody final Movement movement) {
        return movementService.createMovement(movement);
    }

    /**
     * Método encargado de actualizar un movimiento específico.
     * @param id       Id del movimiento a modificar
     * @param movement Cuerpo del movimiento a modificar
     * @return Retorno del cuerpo del movimiento modificado
     */
    @PutMapping("/{id}")
    public Mono<Movement> updateMovement(
        @PathVariable final String id, 
        @RequestBody final Movement movement) {
        return movementService.updateMovement(id, movement);
    }

    /**
     * Método encargado de eliminar un movimiento específico.
     * @param id Id del movimiento a eliminar
     * @return Retorna un Void sobre la eliminacion de un movimiento
     */
    @DeleteMapping("/{id}")
    public Mono<Void> deleteMovement(@PathVariable String id) {
        return movementService.deleteMovement(id);
    }

    /**
     * Método encargado de devolver los movimientos según el productId y el productType.
     * @param productId Id del producto a buscar los movimientos
     * @param productTypeId Id del tipo de producto para buscar movimientos.
     * @return Retorna la lista de movimientos que concuerdan 
     */
    @GetMapping("/report/commission/{productId}")
    public Flux<Movement> reportCommission(@PathVariable String productId, 
        @RequestParam("productTypeId") Integer productTypeId) {
        return movementService.reportCommission(productId, productTypeId);
    }
    
    /**
     * Método encargado de devolver los movimientos según el productId y el productType.
     * @param productId Id del producto a buscar los movimientos
     * @param productTypeId Id del tipo de producto para buscar movimientos.
     * @return Retorna la lista de movimientos que concuerdan
     */
    @GetMapping("/report/{productId}")
    public Flux<Movement> reportMovements(@PathVariable String productId,
        @RequestParam("productTypeId") Integer productTypeId) {
        return movementService.reportMovements(productId, productTypeId);
    }

    /**
     * Método encargado de devolver los últimos 10 movimientos de una tarjeta por su productId.
     * @param productsId Ids de los productos relacionados con la tarjeta
     * @param productTypeId Tipo de los productos que se buscarán
     * @return Retorna los últimos 10 movimientos de una tarjeta de crédito o debito.
     */
    @GetMapping("/report/card")
    public Flux<Movement> reportLastMovementsByCard(@RequestParam String productsId, 
        @RequestParam Integer productTypeId) {
        return movementService.reportLastMovementsByCard(productsId, productTypeId);
    }
}
