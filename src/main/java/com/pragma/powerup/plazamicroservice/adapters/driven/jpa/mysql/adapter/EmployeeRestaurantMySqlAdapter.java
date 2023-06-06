package com.pragma.powerup.plazamicroservice.adapters.driven.jpa.mysql.adapter;

import com.pragma.powerup.plazamicroservice.adapters.driven.jpa.mysql.exceptions.EmployeeNotAssignedToRestaurantException;
import com.pragma.powerup.plazamicroservice.adapters.driven.jpa.mysql.repositories.IEmployeeRestaurantRepository;
import com.pragma.powerup.plazamicroservice.domain.spi.IEmployeeRestaurantPersistencePort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class EmployeeRestaurantMySqlAdapter implements IEmployeeRestaurantPersistencePort {

    private final IEmployeeRestaurantRepository employeeRestaurantRepository;

    @Override
    public Long findRestaurantIdByEmployeeId(Long idEmployee) {
        return employeeRestaurantRepository.findByEmployeeId(idEmployee)
                .orElseThrow(EmployeeNotAssignedToRestaurantException::new)
                .getRestaurant().getId();
    }
}
