package com.pragma.powerup.plazamicroservice.adapters.driving.http.mapper;
import com.pragma.powerup.plazamicroservice.adapters.driving.http.dto.request.DishRequestDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import com.pragma.powerup.plazamicroservice.domain.model.Dish;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IDishRequestMapper {

    @Mapping(source = "idCategory", target = "category.id")
    @Mapping(source = "idPlaza", target = "plaza.id")
    Dish DishRequestDtoToDish(DishRequestDto dishRequestDto);
}
