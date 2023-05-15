package com.pragma.powerup.plazamicroservice.adapters.driving.http.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PlazaResponseDto {
    private Long id;
    private Long nit;
    private String name;
    private String address;
    private String phone;
    private String urlLogo;
    private Long id_propietario;
}
