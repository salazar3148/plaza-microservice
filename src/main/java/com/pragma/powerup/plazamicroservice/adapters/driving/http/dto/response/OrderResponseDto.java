package com.pragma.powerup.plazamicroservice.adapters.driving.http.dto.response;

import com.pragma.powerup.plazamicroservice.domain.model.Plaza;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
public class OrderResponseDto {
    private Long id;
    private LocalDate date;
    private String status;
    private Plaza restaurant;
    private Long idCustomer;
    private Long idChef;
}
