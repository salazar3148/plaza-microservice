package com.pragma.powerup.plazamicroservice.domain.model;

public class Plaza {
    private Long id;
    private Long nit;
    private String name;
    private String address;
    private String phone;
    private String urlLogo;
    private Long id_propietario;

    public Plaza() {
    }

    public Plaza(Long id, Long nit, String name, String address, String phone, String urlLogo, Long id_propietario) {
        this.id = id;
        this.nit = nit;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.urlLogo = urlLogo;
        this.id_propietario = id_propietario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNit() {
        return nit;
    }

    public void setNit(Long nit) {
        this.nit = nit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUrlLogo() {
        return urlLogo;
    }

    public void setUrlLogo(String urlLogo) {
        this.urlLogo = urlLogo;
    }

    public Long getId_propietario() {
        return id_propietario;
    }

    public void setId_propietario(Long id_propietario) {
        this.id_propietario = id_propietario;
    }
}
