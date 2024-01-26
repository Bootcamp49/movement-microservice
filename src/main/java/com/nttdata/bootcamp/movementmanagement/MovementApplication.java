package com.nttdata.bootcamp.movementmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Clase principal para ejecutar el microservicio de movimientos.
 */
@SpringBootApplication
public class MovementApplication {
    public static void main(String[] args) {
        SpringApplication.run(MovementApplication.class, args);
    }

}
