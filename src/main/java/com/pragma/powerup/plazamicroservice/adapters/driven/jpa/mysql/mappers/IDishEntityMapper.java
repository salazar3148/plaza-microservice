package com.pragma.powerup.plazamicroservice.adapters.driven.jpa.mysql.mappers;

import com.pragma.powerup.plazamicroservice.adapters.driven.jpa.mysql.entity.DishEntity;
import com.pragma.powerup.plazamicroservice.domain.model.Dish;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IDishEntityMapper {
    DishEntity dishToDishEntity(Dish dish);
    Dish dishEntityToDish(DishEntity dishEntity);
    default Page<Dish> dishPageEntityToDishPage(Page<DishEntity> dishEntityPage){
        return dishEntityPage.map(this::dishEntityToDish);
    }
}
