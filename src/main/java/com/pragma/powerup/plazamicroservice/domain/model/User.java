package com.pragma.powerup.plazamicroservice.domain.model;

public class User {
    private Long id;
    private Long idRole;
    private String phone;

    public User() {
    }

    public User(Long id, Long idRole, String phone) {
        this.id = id;
        this.idRole = idRole;
        this.phone = phone;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
