package com.pragma.powerup.plazamicroservice.domain.model;

public class Dish {
    private Long id;
    private String name;
    private String description;
    private Integer price;
    private String urlImagen;
    private Boolean status;
    private Category category;
    private Plaza plaza;

    public Dish() {
    }

    public Dish(Long id, String name, String description, Integer price, String urlImagen, Boolean status, Category category, Plaza plaza) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.urlImagen = urlImagen;
        this.status = status;
        this.category = category;
        this.plaza = plaza;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getUrlImagen() {
        return urlImagen;
    }

    public void setUrlImagen(String urlImagen) {
        this.urlImagen = urlImagen;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Plaza getPlaza() {
        return plaza;
    }

    public void setPlaza(Plaza plaza) {
        this.plaza = plaza;
    }
}
