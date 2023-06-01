package com.pragma.powerup.plazamicroservice.adapters.driving.http.controller;

import com.pragma.powerup.plazamicroservice.adapters.driving.http.dto.request.DishRequestDto;
import com.pragma.powerup.plazamicroservice.adapters.driving.http.dto.request.DishUpdateRequestDto;
import com.pragma.powerup.plazamicroservice.adapters.driving.http.handlers.impl.DishHandlerImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

import static com.pragma.powerup.plazamicroservice.configuration.Constants.DISH_CREATED_MESSAGE;
import static com.pragma.powerup.plazamicroservice.configuration.Constants.DISH_UPDATED_MESSAGE;
import static com.pragma.powerup.plazamicroservice.configuration.Constants.RESPONSE_MESSAGE_KEY;

@RestController
@RequestMapping("/dishes")
@RequiredArgsConstructor
public class DishRestController {

    private final DishHandlerImpl dishHandler;
    @PostMapping
    public ResponseEntity<Map<String, String>> saveDish(@RequestHeader("Authorization") String token, @Valid @RequestBody DishRequestDto dishRequestDto){
        dishHandler.saveDish(token, dishRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Collections.singletonMap(RESPONSE_MESSAGE_KEY, DISH_CREATED_MESSAGE));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Map<String, String>> updateDish(@RequestHeader("Authorization") String token, @PathVariable Long id, @Valid @RequestBody DishUpdateRequestDto dishUpdateRequestDto) {
        dishHandler.updateDish(token, id, dishUpdateRequestDto);
        return ResponseEntity.status(HttpStatus.OK)
                .body(Collections.singletonMap(RESPONSE_MESSAGE_KEY, DISH_UPDATED_MESSAGE));
    }
}
