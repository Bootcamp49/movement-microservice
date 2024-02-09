package com.nttdata.bootcamp.movementmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Clase principal para ejecutar el microservicio de movimientos.
 */
@EnableDiscoveryClient
@SpringBootApplication
public class MovementApplication {
    public static void main(String[] args) {
        SpringApplication.run(MovementApplication.class, args);
    }

}
