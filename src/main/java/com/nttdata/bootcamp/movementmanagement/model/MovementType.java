package com.nttdata.bootcamp.movementmanagement.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Clase para los tipos de movimiento.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MovementType {
    Integer id;

    String description;
}
