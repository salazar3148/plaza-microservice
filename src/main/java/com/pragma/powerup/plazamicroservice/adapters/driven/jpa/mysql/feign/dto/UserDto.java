package com.pragma.powerup.plazamicroservice.adapters.driven.jpa.mysql.feign.dto;
import lombok.AllArgsConstructor;
import lombok.Getter;
@AllArgsConstructor
@Getter
public class UserDto {
    private Long id;
    private String phone;
    private Long idRole;
    private String mail;
}
