package com.pragma.powerup.plazamicroservice.adapters.driven.jpa.mysql.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "RESTAURANTS")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PlazaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long nit;
    private String name;
    private String address;
    private String phone;
    private String urlLogo;
    @Column(name = "id_owner")
    private Long idOwner;
}
