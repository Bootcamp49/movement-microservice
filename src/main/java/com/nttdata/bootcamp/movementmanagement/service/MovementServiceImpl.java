package com.nttdata.bootcamp.movementmanagement.service;

import com.nttdata.bootcamp.movementmanagement.model.Movement;
import com.nttdata.bootcamp.movementmanagement.repository.MovementRepository;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
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
    @CircuitBreaker(name = "movement", fallbackMethod = "multipleMovementsFallback")
    public Flux<Movement> findMovements() {
        return movementRepository.findAll();
    }

    @Override
    @CircuitBreaker(name = "movement", fallbackMethod = "singleMovementFallback")
    public Mono<Movement> findById(@NonNull String id) {
        return movementRepository.findById(id);
    }

    @Override
    @CircuitBreaker(name = "movement", fallbackMethod = "multipleMovementsFallback")
    public Flux<Movement> findByClientId(String clientId) {
        return movementRepository.findByClientId(clientId);
    }

    @Override
    @CircuitBreaker(name = "movement", fallbackMethod = "multipleMovementsFallback")
    public Flux<Movement> findByProductId(String productId) {
        return movementRepository.findByProductId(productId);
    }

    @Override
    @CircuitBreaker(name = "movement", fallbackMethod = "singleMovementFallback")
    public Mono<Movement> createMovement(Movement movement) {
        movement.setMovementDate(LocalDateTime.now());
        movement.setIsFromDebitCard(
            movement.getIsFromDebitCard() != null ? movement.getIsFromDebitCard(): false
        );
        return movementRepository.save(movement);
    }

    @Override
    @CircuitBreaker(name = "movement", fallbackMethod = "singleMovementFallback")
    public Mono<Movement> updateMovement(@NonNull String id, Movement movement) {
        return movementRepository.findById(id)
                .flatMap(existingMovement -> {
                    existingMovement.setMovementDate( movement.getMovementDate() != null ?
                            movement.getMovementDate() : existingMovement.getMovementDate());
                    existingMovement.setAmountMoved( movement.getAmountMoved() != null ?
                            movement.getAmountMoved() : existingMovement.getAmountMoved());
                    existingMovement.setProductId(movement.getProductId() != null ?
                            movement.getProductId() : existingMovement.getProductId());
                    existingMovement.setType(movement.getType() != null ?
                            movement.getType() : existingMovement.getType());
                    existingMovement.setClientId(movement.getClientId() != null ?
                            movement.getClientId() : existingMovement.getClientId());
                    return movementRepository.save(existingMovement);
                });
    }

    @Override
    public Mono<Void> deleteMovement(@NonNull String id) {
        return movementRepository.deleteById(id);
    }

    @Override
    @CircuitBreaker(name = "movement", fallbackMethod = "multipleMovementsFallback")
    public Flux<Movement> reportCommission(String productId, Integer productTypeId) {
        List<Integer> movementTypeIds = new ArrayList<Integer>();
        if (productTypeId == 1) {
            movementTypeIds.add(1);
            movementTypeIds.add(2);
        } else {
            movementTypeIds.add(3);
            movementTypeIds.add(4);
        }

        return movementRepository.findByProductId(productId)
                .filter((p) -> {
                    return movementTypeIds.contains(p.getType().getId()) && p.getHasCommission();
                });
    }

    @Override
    @CircuitBreaker(name = "movement", fallbackMethod = "multipleMovementsFallback")
    public Flux<Movement> reportMovements(String productId, Integer productTypeId) {
        List<Integer> movementTypeIds = new ArrayList<Integer>();
        if (productTypeId == 1) {
            movementTypeIds.add(1);
            movementTypeIds.add(2);
        } else {
            movementTypeIds.add(3);
            movementTypeIds.add(4);
        }

        Month currentMonth = LocalDateTime.now().getMonth();
        return movementRepository.findByProductId(productId)
            .filter((p) -> {
                return movementTypeIds.contains(p.getType().getId()) 
                    && p.getMovementDate().getMonth() == currentMonth;
            });
    }

    @Override
    @CircuitBreaker(name = "movement", fallbackMethod = "multipleMovementsFallback")
    public Flux<Movement> reportLastMovementsByCard(String productsId, Integer productTypeId) {
        List<String> productsSeparated = Arrays.asList(productsId.split(","));
        if (productsSeparated.size() <= 0) {
            return null;
        }
        List<Integer> movementTypes = new ArrayList<>();
        if (productTypeId == 1) {
            movementTypes.add(1);
            movementTypes.add(2);
        } else {
            movementTypes.add(3);
            movementTypes.add(4);
        }

        Flux<Movement> movements = movementRepository.findAll().filter(
            m -> {
                return (productsSeparated.contains(m.getProductId())
                    || productsSeparated.contains(m.getProductOriginId()))
                    && (productTypeId == 1 ? m.getIsFromDebitCard() : true);
            }
        ).filter(m -> movementTypes.contains(m.getType().getId()))
            .sort(Comparator.comparing(Movement::getMovementDate).reversed())
            .take(10);
        return movements;
    }

    private Mono<Movement> singleMovementFallback(Throwable throwable){
        Movement movementToReturn = new Movement();
        return Mono.just(movementToReturn);
    }
    private Flux<Movement> multipleMovementsFallback(Throwable throwable){
        Movement movementToReturn = new Movement();
        return Flux.just(movementToReturn);
    }
}
