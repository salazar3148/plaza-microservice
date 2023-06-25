package com.pragma.powerup.plazamicroservice.domain.model.user;

public class User {
    private Long id;
    private Long idRole;
    private String phone;

    private String mail;

    public User() {
    }

    public User(Long id, Long idRole, String phone, String mail) {
        this.id = id;
        this.idRole = idRole;
        this.phone = phone;
        this.mail = mail;
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

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}
