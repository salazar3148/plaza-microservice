package com.pragma.powerup.plazamicroservice.adapters.driving.http.mapper;

import com.pragma.powerup.plazamicroservice.adapters.driving.http.dto.response.DishResponseDto;
import com.pragma.powerup.plazamicroservice.domain.model.Dish;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IDishResponseMapper {

    @Mapping(source = "plaza.name", target = "restaurantName")
    DishResponseDto dishtoToDishResponseDto(Dish dish);

    default Page<DishResponseDto> dishPageToDishResponseDtoPage(Page<Dish> dishPage) {
        return dishPage.map(this::dishtoToDishResponseDto);
    }
}
