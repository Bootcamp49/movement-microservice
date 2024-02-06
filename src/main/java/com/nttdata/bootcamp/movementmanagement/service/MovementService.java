package com.nttdata.bootcamp.movementmanagement.service;

import com.nttdata.bootcamp.movementmanagement.model.Movement;
import lombok.NonNull;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Interfaz de movimientos.
 */
public interface MovementService {
    /**
     * Método para encontrar todos los movimientos.
     * @return Retorna todos los movimientos registrados
     */
    Flux<Movement> findMovements();

    /**
     * Método para encontrar un movimiento específico en base a su id.
     * @param id Id de un movimiento especifico
     * @return Retorna un movimiento especifico
     */
    Mono<Movement> findById(@NonNull String id);

    /**
     * Método para encontrar movimientos por el id de un cliente.
     * @param clientId Id de un cliente para obtener sus movimientos
     * @return Retorna todos los movimientos de un cliente
     */
    Flux<Movement> findByClientId(String clientId);

    /**
     * Método para encontrar los movimientos por un producto.
     * @param productId Id de un producto para obtener sus movimientos
     * @return Retorna todos los movimientos de un producto
     */
    Flux<Movement> findByProductId(String productId);

    /**
     * Método para crear un nuevo movimiento.
     * @param movement Cuerpo a crear de un movimiento
     * @return Retorna el cuerpo del movimiento creado
     */
    Mono<Movement> createMovement(Movement movement);

    /**
     * Método para actualizar los movimientos.
     * @param id Id del movimiento a actualizar
     * @param movement Cuerpo del movimiento a actualizar
     * @return Retorna el cuerpo del movimiento actualizado
     */
    Mono<Movement> updateMovement(String id, Movement movement);

    /**
     * Método para eliminar movimientos.
     * @param id Id del movimiento a eliminar
     * @return Retorna un Void sobre la eliminación del movimiento
     */
    Mono<Void> deleteMovement(String id);

    /**
     * Método para obtener el reporte de comisiones de un producto.
     * @param productId Id del producto del cual sacar el reporte de comisiones.
     * @param productTypeId Id del tipo de producto del cual se va a sacar el reporte.
     * @return retornar los movimientos con comisiones del producto.
     */
    Flux<Movement> reportCommission(String productId, Integer productTypeId);

    /**
     * Método para obtener el reporte movimientos del mes actual.
     * @param productId Id del producto del cual obtener el reporte de movimientos.
     * @param productTypeId Id del tipo de producto del cual se va a sacar el reporte.
     * @return Retorna la lista de movimientos del mes actual del producto indicado.
     */
    Flux<Movement> reportMovements(String productId, Integer productTypeId);

    /**
     * Método para obtener el reporte de los últimos 10 movimientos de la tarjeta.
     * @param productsId Ids de los productos del cual obtener los movimientos
     * @param productTypeId Id del tipo de producto de los cuales buscar los movimientos
     * @return retorna los últimos 10 movimientos de los productos asociados a las tarjetas.
     */
    Flux<Movement> reportLastMovementsByCard(String productsId, Integer productTypeId);
}
