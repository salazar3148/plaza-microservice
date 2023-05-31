package com.pragma.powerup.plazamicroservice.domain.model;

public class User {
    private Long id;
    private Long idRole;

    public User() {
    }

    public User(Long id, Long idRole) {
        this.id = id;
        this.idRole = idRole;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdRole() {
        return idRole;
    }

    public void setIdRole(Long idRole) {
        this.idRole = idRole;
    }
}
