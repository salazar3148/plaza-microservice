package com.pragma.powerup.plazamicroservice.adapters.driven.jpa.mysql.repositories;

import com.pragma.powerup.plazamicroservice.adapters.driven.jpa.mysql.entity.OrderDetailsEntity;
import com.pragma.powerup.plazamicroservice.adapters.driven.jpa.mysql.entity.OrderDetailsId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOrderDetailsRepository extends JpaRepository<OrderDetailsEntity, OrderDetailsId> {
}
