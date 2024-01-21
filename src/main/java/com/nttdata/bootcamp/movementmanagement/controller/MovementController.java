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
@RequestMapping("movement")
public class MovementController {
    @Autowired
    private MovementService service;
    
    @GetMapping()
    public Flux<Movement> findMovements(){
        return service.findMovements();
    }

    @GetMapping("/{id}")
    public Mono<Movement> findById(@PathVariable String id) {
        return service.findById(id);
    }
    
    @GetMapping("/client/{clientId}")
    public Flux<Movement> findMovementsByClientId(@PathVariable String clientId){
        return service.findByClientId(clientId);
    }

    @GetMapping("/product/{productId}")
    public Flux<Movement> findMovementsByProductId(@PathVariable String productId){
        return service.findByProductId(productId);
    }

    @PostMapping()
    public Mono<Movement> createMovement(@RequestBody Movement movement){
        return service.createMovement(movement);
    }

    @PutMapping("/{id}")
    public Mono<Movement> updateMovement(@PathVariable String id, @RequestBody Movement movement){
        return service.updateMovement(id, movement);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteMovement(@PathVariable String id){
        return service.deleteMovementt(id);
    }
}
