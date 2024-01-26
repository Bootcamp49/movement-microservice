package com.nttdata.bootcamp.movementmanagement.service;

import com.nttdata.bootcamp.movementmanagement.model.Movement;
import com.nttdata.bootcamp.movementmanagement.repository.MovementRepository;
import java.time.LocalDate;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Clase de implementación de la interfaz de Movimientos.
 */
@RequiredArgsConstructor
@Service
public class MovementServiceImpl implements MovementService {

    @Autowired
    private final MovementRepository movementRepository;

    @Override
    public Flux<Movement> findMovements() {
        return movementRepository.findAll();
    }

    @Override
    public Mono<Movement> findById(@NonNull String id) {
        return movementRepository.findById(id);
    }

    @Override
    public Flux<Movement> findByClientId(String clientId) {
        return movementRepository.findByClientId(clientId);
    }

    @Override
    public Flux<Movement> findByProductId(String productId) {
        return movementRepository.findByProductId(productId);
    }

    @Override
    public Mono<Movement> createMovement(Movement movement) {
        movement.setMovementdDate(LocalDate.now());
        return movementRepository.save(movement);
    }

    @Override
    public Mono<Movement> updateMovement(@NonNull String id, Movement movement) {
        return movementRepository.findById(id)
        .flatMap(existingMovement -> {
            existingMovement.setMovementdDate(movement.getMovementdDate());
            existingMovement.setAmountMoved(movement.getAmountMoved());
            existingMovement.setProductId(movement.getProductId());
            existingMovement.setType(movement.getType());
            return movementRepository.save(existingMovement);
        });
    }

    @Override
    public Mono<Void> deleteMovement(@NonNull String id) {
        return movementRepository.deleteById(id);
    }
    
}
