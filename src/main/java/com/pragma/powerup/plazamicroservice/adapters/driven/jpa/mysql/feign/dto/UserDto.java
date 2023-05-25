package com.pragma.powerup.plazamicroservice.adapters.driven.jpa.mysql.feign.dto;


import com.pragma.powerup.plazamicroservice.domain.model.Role;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class UserDto {
    private Long id;
    private String name;
    private String surname;
    private String dniNumber;
    private String phone;
    private LocalDate dateBirthday;
    private String mail;
    private Role id_role;
}
