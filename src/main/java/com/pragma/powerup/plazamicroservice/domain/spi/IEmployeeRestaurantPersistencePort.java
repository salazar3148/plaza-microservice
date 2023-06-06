package com.pragma.powerup.plazamicroservice.domain.spi;

public interface IEmployeeRestaurantPersistencePort {
    Long findRestaurantIdByEmployeeId(Long idEmployee);
}
