package com.nttdata.bootcamp.movementmanagement.controller;

import org.springframework.web.bind.annotation.RestController;

import com.nttdata.bootcamp.movementmanagement.model.Movement;
import com.nttdata.bootcamp.movementmanagement.service.MovementService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/movement")
public class MovementController {
    /**
     * Interface del servicio de movimientos
     */
    @Autowired
    private MovementService movementService;
    
    /**
     * @return Retorno de todos los movimientos realizados
     */
    @GetMapping()
    public Flux<Movement> findMovements(){
        return movementService.findMovements();
    }

    /**
     * @param id Id del movimiento especifico a retornar
     * @return Retorno de un movimiento especifico
     */
    @GetMapping("/{id}")
    public Mono<Movement> findById(@PathVariable String id) {
        return movementService.findById(id);
    }
    
    /**
     * @param clientId Id del cliente del cual buscar sus movimientos
     * @return Retorna todos los movimientos de un cliente
     */
    @GetMapping("/client/{clientId}")
    public Flux<Movement> findMovementsByClientId(@PathVariable String clientId){
        return movementService.findByClientId(clientId);
    }

    /**
     * @param productId Id del producto del cual buscar sus movimientos
     * @return Retorna todos los movimientos de un producto
     */
    @GetMapping("/product/{productId}")
    public Flux<Movement> findMovementsByProductId(@PathVariable String productId){
        return movementService.findByProductId(productId);
    }

    /**
     * @param movement Cuerpo a crear de un movimiento
     * @return Retorno del cuerpo del movimiento creado
     */
    @PostMapping()
    public Mono<Movement> createMovement(@RequestBody Movement movement){
        return movementService.createMovement(movement);
    }

    /**
     * @param id Id del movimiento a modificar
     * @param movement Cuerpo del movimiento a modificar
     * @return Retorno del cuerpo del movimiento modificado
     */
    @PutMapping("/{id}")
    public Mono<Movement> updateMovement(@PathVariable String id, @RequestBody Movement movement){
        return movementService.updateMovement(id, movement);
    }

    /**
     * @param id Id del movimiento a eliminar
     * @return Retorna un Void sobre la eliminacion de un movimiento
     */
    @DeleteMapping("/{id}")
    public Mono<Void> deleteMovement(@PathVariable String id){
        return movementService.deleteMovement(id);
    }
}
