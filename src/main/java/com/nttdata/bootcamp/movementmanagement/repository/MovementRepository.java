package com.nttdata.bootcamp.movementmanagement.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.nttdata.bootcamp.movementmanagement.model.Movement;

import reactor.core.publisher.Flux;

public interface MovementRepository extends ReactiveCrudRepository<Movement, String>{
    
    Flux<Movement> findByClientId(String clientId);
    
    Flux<Movement> findByProductId(String productId);
}
