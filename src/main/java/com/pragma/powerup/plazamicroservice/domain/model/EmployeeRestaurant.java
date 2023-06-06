package com.pragma.powerup.plazamicroservice.domain.model;

public class EmployeeRestaurant {
    Long employeeId;

    Plaza restaurant;

    public EmployeeRestaurant() {
    }

    public EmployeeRestaurant(Long employeeId, Plaza restaurant) {
        this.employeeId = employeeId;
        this.restaurant = restaurant;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public Plaza getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Plaza restaurant) {
        this.restaurant = restaurant;
    }
}
