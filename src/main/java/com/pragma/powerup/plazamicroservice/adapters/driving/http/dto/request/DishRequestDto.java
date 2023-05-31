package com.pragma.powerup.plazamicroservice.adapters.driving.http.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class DishRequestDto {
    @NotBlank
    private String name;
    @Min(value = 1, message = "the price must have a value greater than zero")
    @NotNull
    private Integer price;
    @NotBlank
    private String description;
    @NotBlank
    private String urlImagen;
    @NotNull
    private Long idCategory;
    @NotNull
    private Long idPlaza;
}
