package com.pragma.powerup.plazamicroservice.adapters.driven.jpa.mysql.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "ORDERS_DISHES")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderDetailsEntity {

    @EmbeddedId
    private OrderDetailsId id;

    @ManyToOne
    @MapsId("idOrder")
    @JoinColumn(name = "id_order")
    OrderEntity order;

    @ManyToOne
    @MapsId("idDish")
    @JoinColumn(name = "id_dish")
    DishEntity dish;

    Integer quantity;
}
