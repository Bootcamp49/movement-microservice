package com.nttdata.bootcamp.movementmanagement.repository;

import com.nttdata.bootcamp.movementmanagement.model.Movement;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

/**
 * Repositorio para la colección de documentos de la BD.
 */
public interface MovementRepository extends ReactiveCrudRepository<Movement, String> {

    Flux<Movement> findByClientId(String clientId);

    Flux<Movement> findByProductId(String productId);
}
