package com.pragma.powerup.plazamicroservice.adapters.driving.http.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PlazaRequestDto {
    @NotNull
    private Long nit;
    @Pattern(regexp = "^(?=.*[a-zA-Z])[a-zA-Z0-9 ]*$", message = "the name can contain numbers but not only numbers")
    private String name;
    @NotBlank
    private String address;
    @Size(min= 10, max = 13, message = "size phone [10 - 13]")
    @Pattern(regexp = "^[+]?[0-9]+$", message = "numeric field, (optional) start with the area code of your country")
    private String phone;
    @NotBlank
    private String urlLogo;
    @NotNull
    private Long idPropietario;
}
