package com.pragma.powerup.plazamicroservice.adapters.driven.jpa.mysql.repositories;

import com.pragma.powerup.plazamicroservice.adapters.driven.jpa.mysql.entity.OrderEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOrderRepository extends JpaRepository<OrderEntity, Long> {
    boolean existsById(Long id);
    boolean existsByStatusAndIdCustomer(String status, Long idCustomer);
    Page<OrderEntity> findAllByRestaurantIdAndStatus(Long restaurantId, String status, Pageable pageable);
}
