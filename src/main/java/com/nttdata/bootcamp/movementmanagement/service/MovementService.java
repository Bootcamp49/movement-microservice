package com.nttdata.bootcamp.movementmanagement.service;

import com.nttdata.bootcamp.movementmanagement.model.Movement;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface MovementService {
    /**
     * @return Retorna todos los movimientos registrados
     */
    Flux<Movement> findMovements();

    /**
     * @param id Id de un movimiento especifico
     * @return Retorna un movimiento especifico
     */
    Mono<Movement> findById(String id);

    /**
     * @param clientId Id de un cliente para obtener sus movimientos
     * @return Retorna todos los movimientos de un cliente
     */
    Flux<Movement> findByClientId(String clientId);

    /**
     * @param productId Id de un producto para obtener sus movimientos
     * @return Retorna todos los movimientos de un producto
     */
    Flux<Movement> findByProductId(String productId);

    /**
     * @param movement Cuerpo a crear de un movimiento
     * @return Retorna el cuerpo del movimiento creado
     */
    Mono<Movement> createMovement(Movement movement);

    /**
     * @param id Id del movimiento a actualizar
     * @param movement Cuerpo del movimiento a actualizar
     * @return Retorna el cuerpo del movimiento actualizado
     */
    Mono<Movement> updateMovement(String id, Movement movement);

    /**
     * @param id Id del movimiento a eliminar
     * @return Retorna un Void sobre la eliminación del movimiento
     */
    Mono<Void> deleteMovement(String id);
}
