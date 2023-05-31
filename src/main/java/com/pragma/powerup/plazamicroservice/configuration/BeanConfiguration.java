package com.pragma.powerup.plazamicroservice.configuration;

import com.pragma.powerup.plazamicroservice.adapters.driven.jpa.mysql.adapter.CategoryMySqlAdapter;
import com.pragma.powerup.plazamicroservice.adapters.driven.jpa.mysql.adapter.DishMySqlAdapter;
import com.pragma.powerup.plazamicroservice.adapters.driven.jpa.mysql.adapter.PlazaMysqlAdapter;
import com.pragma.powerup.plazamicroservice.adapters.driven.jpa.mysql.adapter.UserServiceAdapter;
import com.pragma.powerup.plazamicroservice.adapters.driven.jpa.mysql.feign.client.UserFeignClient;
import com.pragma.powerup.plazamicroservice.adapters.driven.jpa.mysql.mappers.ICategoryEntityMapper;
import com.pragma.powerup.plazamicroservice.adapters.driven.jpa.mysql.mappers.IDishEntityMapper;
import com.pragma.powerup.plazamicroservice.adapters.driven.jpa.mysql.mappers.IPlazaEntityMapper;
import com.pragma.powerup.plazamicroservice.adapters.driven.jpa.mysql.mappers.IUserMapper;
import com.pragma.powerup.plazamicroservice.adapters.driven.jpa.mysql.repositories.ICategoryRepository;
import com.pragma.powerup.plazamicroservice.adapters.driven.jpa.mysql.repositories.IDishRepository;
import com.pragma.powerup.plazamicroservice.adapters.driven.jpa.mysql.repositories.IPlazaRepository;
import com.pragma.powerup.plazamicroservice.domain.api.IDishServicePort;
import com.pragma.powerup.plazamicroservice.domain.api.IPlazaServicePort;
import com.pragma.powerup.plazamicroservice.domain.spi.ICategoryPersistencePort;
import com.pragma.powerup.plazamicroservice.domain.spi.IDishPersistencePort;
import com.pragma.powerup.plazamicroservice.domain.spi.IPlazaPersistencePort;
import com.pragma.powerup.plazamicroservice.domain.spi.IUserServicePort;
import com.pragma.powerup.plazamicroservice.domain.usecase.DishUseCase;
import com.pragma.powerup.plazamicroservice.domain.usecase.PlazaUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {
    private final IPlazaRepository plazaRepository;
    private final IPlazaEntityMapper plazaEntityMapper;

    private final IDishRepository dishRepository;
    private final IDishEntityMapper dishEntityMapper;

    private final ICategoryRepository categoryRepository;
    private final ICategoryEntityMapper categoryEntityMapper;

    private final UserFeignClient userFeignClient;
    private final IUserMapper userMapper;
    @Bean
    public IPlazaPersistencePort plazaPersistencePort() {
        return new PlazaMysqlAdapter(plazaRepository, plazaEntityMapper);
    }

    @Bean
    public ICategoryPersistencePort categoryPersistencePort() {
        return new CategoryMySqlAdapter(categoryRepository, categoryEntityMapper);
    }

    @Bean
    public IDishPersistencePort dishPersistencePort(){
        return new DishMySqlAdapter(dishRepository, dishEntityMapper);
    }

    @Bean
    public IUserServicePort userServicePort(){
        return new UserServiceAdapter(userFeignClient, userMapper);
    }

    @Bean
    public IPlazaServicePort plazaServicePort() {
        return new PlazaUseCase(plazaPersistencePort(), userServicePort());
    }

    @Bean
    public IDishServicePort dishServicePort() {
        return new DishUseCase(dishPersistencePort(), categoryPersistencePort(), plazaPersistencePort(), userServicePort());
    }

}
