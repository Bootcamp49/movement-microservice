package com.nttdata.bootcamp.movementmanagement.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.nttdata.bootcamp.movementmanagement.model.Movement;
import com.nttdata.bootcamp.movementmanagement.model.MovementType;
import com.nttdata.bootcamp.movementmanagement.service.MovementService;
import com.nttdata.bootcamp.movementmanagement.util.ConstantsUtil;
import com.nttdata.bootcamp.movementmanagement.util.Util;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@SpringBootTest
class MovementControllerTests {

    @Autowired
    private MovementController movementController;

    @MockBean
    private MovementService movementService;

    private Util util = new Util();

    @Test
    void testCreateMovement() {
        when(movementService.createMovement(any(Movement.class)))
                .thenReturn(Mono.just(movementMockResponse()));
        Mono<Movement> responseController = movementController
                .createMovement(movementMockRequest());
        Mono<Movement> responseToCompare = Mono.just(
                util.serializeArchive(ConstantsUtil.createMovementMock_Success, Movement.class));
        assertThat(responseController).usingRecursiveComparison().isEqualTo(responseToCompare);
    }

    @Test
    void testDeleteMovement() {
        when(movementService.deleteMovement(anyString())).thenReturn(Mono.empty());
        movementController.deleteMovement("1234");
        verify(movementService).deleteMovement(anyString());
    }

    @Test
    void testFindById() {
        when(movementService.findById(anyString())).thenReturn(Mono.just(movementMockResponse()));
        Mono<Movement> responseController = movementController.findById("abcd");
        Mono<Movement> responseToCompare = Mono.just(
                util.serializeArchive(ConstantsUtil.findByIdMovementMock_Success, Movement.class));
        assertThat(responseController).usingRecursiveComparison().isEqualTo(responseToCompare);
    }

    @Test
    void testFindMovements() {
        when(movementService.findMovements()).thenReturn(Flux.just(movementMockResponse()));
        Flux<Movement> responseController = movementController.findMovements();
        Flux<Movement> responseToCompare = Flux.just(
                util.serializeArchive(ConstantsUtil.findAllMovementsMock_Success,
                        Movement[].class));
        assertThat(responseController).usingRecursiveComparison().isEqualTo(responseToCompare);
    }

    @Test
    void testFindMovementsByClientId() {
        when(movementService.findByClientId(anyString()))
                .thenReturn(Flux.just(movementMockResponse()));
        Flux<Movement> responseController = movementController
                .findMovementsByClientId("clientId1234");
        Flux<Movement> responseToCompare = Flux.just(
                util.serializeArchive(ConstantsUtil.findByClientIdMock_Success, Movement[].class));
        assertThat(responseController).usingRecursiveComparison().isEqualTo(responseToCompare);
    }

    @Test
    void testFindMovementsByProductId() {
        when(movementService.findByProductId(anyString()))
                .thenReturn(Flux.just(movementMockResponse()));
        Flux<Movement> responseController = movementController
                .findMovementsByProductId("productId1234");
        Flux<Movement> responseToCompare = Flux.just(
                util.serializeArchive(ConstantsUtil.findByProductIdMock_Success, Movement[].class));
        assertThat(responseController).usingRecursiveComparison().isEqualTo(responseToCompare);
    }

    @Test
    void testUpdateMovement() {
        when(movementService.updateMovement(anyString(), any(Movement.class)))
                .thenReturn(Mono.just(movementMockResponse()));
        Movement mockedRequest = movementMockRequest();
        Mono<Movement> responsemovementController = movementController
                .updateMovement("abcd1234", mockedRequest);
        Mono<Movement> responseToCompare = Mono.just(
                util.serializeArchive(ConstantsUtil.updateMovementMock_Success, Movement.class));
        assertThat(responsemovementController)
                .usingRecursiveComparison().isEqualTo(responseToCompare);
    }

    private Movement movementMockResponse() {
        Movement movementMocked = new Movement();
        MovementType movementType = new MovementType();
        movementType.setId(1);
        movementType.setDescription("Debito");

        movementMocked.setId("abcd1234");
        movementMocked.setMovementDate(LocalDateTime.parse("2024-01-28T05:00:00.00"));
        movementMocked.setProductId("productId1234");
        movementMocked.setType(movementType);
        movementMocked.setAmountMoved(10.0);
        movementMocked.setClientId("clientId1234");
        return movementMocked;
    }

    private Movement movementMockRequest() {
        Movement movementMocked = new Movement();
        MovementType movementType = new MovementType();
        movementType.setId(1);
        movementType.setDescription("Debito");

        movementMocked.setAmountMoved(10.0);
        movementMocked.setMovementDate(LocalDateTime.parse("2024-01-28T05:00:00.00"));
        movementMocked.setProductId("productId1234");
        movementMocked.setType(movementType);
        movementMocked.setClientId("clientId1234");
        return movementMocked;
    }

}
