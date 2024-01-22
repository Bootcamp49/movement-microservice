package com.nttdata.bootcamp.movementmanagement.model;

import java.time.LocalDate;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document(collection = "movement")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
/*
 * Clase de movimientos sobre los productos
 */
public class Movement {
    private String id;
    private String productId;
    private Double amountMoved;
    private MovementType type;
    private LocalDate movementdDate;
}
