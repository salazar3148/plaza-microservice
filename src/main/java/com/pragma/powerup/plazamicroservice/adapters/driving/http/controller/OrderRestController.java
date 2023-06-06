package com.pragma.powerup.plazamicroservice.adapters.driving.http.controller;

import com.pragma.powerup.plazamicroservice.adapters.driving.http.dto.request.OrderRequestDto;
import com.pragma.powerup.plazamicroservice.adapters.driving.http.dto.response.OrderResponseDto;
import com.pragma.powerup.plazamicroservice.adapters.driving.http.handlers.impl.OrderHandlerImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

import static com.pragma.powerup.plazamicroservice.configuration.Constants.PLAZA_CREATED_MESSAGE;
import static com.pragma.powerup.plazamicroservice.configuration.Constants.RESPONSE_MESSAGE_KEY;


@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderRestController {

    private final OrderHandlerImpl orderHandler;

    @Operation(summary = "Add a new order",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Order created",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Map")))
            })
    @PostMapping
    public ResponseEntity<Map<String, String>> savePlaza(@Valid @RequestBody OrderRequestDto orderRequestDto, @RequestHeader("Authorization") String token) {
        orderHandler.createOrder(token, orderRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Collections.singletonMap(RESPONSE_MESSAGE_KEY, PLAZA_CREATED_MESSAGE));
    }

    @Operation(summary = "get order pages by order status",
            responses = {
                    @ApiResponse(responseCode = "200", description = "orders returned",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Map")))

            })
    @GetMapping
    public ResponseEntity<Page<OrderResponseDto>> getOrdersByOrderStatus(@RequestHeader("Authorization") String token, @RequestParam String status, @RequestParam Integer numPage, @RequestParam Integer sizePage) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(orderHandler.getOrdersByStatus(token, numPage, sizePage, status));
    }
}
