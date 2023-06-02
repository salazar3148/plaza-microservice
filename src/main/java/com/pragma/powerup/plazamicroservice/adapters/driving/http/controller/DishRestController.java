package com.pragma.powerup.plazamicroservice.adapters.driving.http.controller;

import com.pragma.powerup.plazamicroservice.adapters.driving.http.dto.request.DishRequestDto;
import com.pragma.powerup.plazamicroservice.adapters.driving.http.dto.request.DishUpdateRequestDto;
import com.pragma.powerup.plazamicroservice.adapters.driving.http.dto.response.DishResponseDto;
import com.pragma.powerup.plazamicroservice.adapters.driving.http.handlers.impl.DishHandlerImpl;
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
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

import static com.pragma.powerup.plazamicroservice.configuration.Constants.DISH_CREATED_MESSAGE;
import static com.pragma.powerup.plazamicroservice.configuration.Constants.DISH_UPDATED_MESSAGE;
import static com.pragma.powerup.plazamicroservice.configuration.Constants.RESPONSE_MESSAGE_KEY;
import static com.pragma.powerup.plazamicroservice.configuration.Constants.STATUS_DISH_UPDATED_MESSAGE;

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

    @Operation(summary = "Enable/disable status dish",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Plaza created",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Map"))),
                    @ApiResponse(responseCode = "404", description = "Dish not found",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Error")))})
    @PatchMapping("/status/{id}")
    public ResponseEntity<Map<String, String>> updateDish(@RequestHeader("Authorization") String token, @PathVariable Long id) {
        dishHandler.updateStatusDish(token, id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(Collections.singletonMap(RESPONSE_MESSAGE_KEY, STATUS_DISH_UPDATED_MESSAGE));
    }
    @Operation(summary = "get dish page by category name",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Plaza created",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Map")))
            })
    @GetMapping
    public ResponseEntity<Page<DishResponseDto>> getDishPageByCategoryName(@RequestHeader("Authorization") String token, @RequestParam String categoryName, @RequestParam Integer numPage, @RequestParam Integer sizePage) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(dishHandler.getPageDishResponseDto(token, categoryName, numPage, sizePage));
    }
}
