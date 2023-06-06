package com.pragma.powerup.plazamicroservice.domain.model;
import com.pragma.powerup.plazamicroservice.domain.model.orderstatus.OrderStatus;
import java.time.LocalDate;

public class Order {
    private Long id;
    private LocalDate date;
    private OrderStatus status;
    private Plaza restaurant;
    private Long idCustomer;
    private Long idChef;

    public Order() {
    }

    public Order(Long id, LocalDate date, OrderStatus status, Plaza restaurant, Long idCustomer, Long idChef) {
        this.id = id;
        this.date = date;
        this.status = status;
        this.restaurant = restaurant;
        this.idCustomer = idCustomer;
        this.idChef = idChef;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public Plaza getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Plaza restaurant) {
        this.restaurant = restaurant;
    }

    public Long getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(Long idCustomer) {
        this.idCustomer = idCustomer;
    }

    public Long getIdChef() {
        return idChef;
    }

    public void setIdChef(Long idChef) {
        this.idChef = idChef;
    }
}
