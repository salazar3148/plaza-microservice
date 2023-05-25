package com.pragma.powerup.plazamicroservice.adapters.driving.http.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PlazaRequestDto {
    private Long nit;
    private String name;
    private String address;
    private String phone;
    private String urlLogo;
    private Long id_propietario;
}
