package com.pragma.powerup.plazamicroservice.configuration;

import com.pragma.powerup.plazamicroservice.adapters.driven.jpa.mysql.adapter.PlazaMysqlAdapter;
import com.pragma.powerup.plazamicroservice.adapters.driven.jpa.mysql.mappers.IPlazaEntityMapper;
import com.pragma.powerup.plazamicroservice.adapters.driven.jpa.mysql.repositories.IPlazaRepository;
import com.pragma.powerup.plazamicroservice.domain.api.IPlazaServicePort;
import com.pragma.powerup.plazamicroservice.domain.spi.IPlazaPersistencePort;
import com.pragma.powerup.plazamicroservice.domain.usecase.PlazaUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {
    private final IPlazaRepository plazaRepository;
    private final IPlazaEntityMapper plazaEntityMapper;

    @Bean
    public IPlazaPersistencePort plazaPersistencePort() {
        return new PlazaMysqlAdapter(plazaRepository, plazaEntityMapper);
    }
    @Bean
    public IPlazaServicePort plazaServicePort() {
        return new PlazaUseCase(plazaPersistencePort());
    }
}
