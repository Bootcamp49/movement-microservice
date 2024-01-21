package com.nttdata.bootcamp.movementmanagement.service;

import com.nttdata.bootcamp.movementmanagement.model.Movement;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface MovementService {
    Flux<Movement> findMovements();

    Mono<Movement> findById(String id);

    Flux<Movement> findByClientId(String clientId);

    Flux<Movement> findByProductId(String productId);

    Mono<Movement> createMovement(Movement movement);

    Mono<Movement> updateMovement(String id, Movement movement);

    Mono<Void> deleteMovementt(String id);
}
