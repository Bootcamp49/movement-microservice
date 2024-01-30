package com.nttdata.bootcamp.movementmanagement.model;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Clase de movimientos sobre los productos.
 */
@Document(collection = "movement")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Movement {
    @Id
    private String id;
    private String productId;
    private Double amountMoved;
    private MovementType type;
    private LocalDateTime movementDate;
    private String clientId;
    private Boolean hasCommission;
    private String productOriginId;
}
