package com.pragma.powerup.plazamicroservice.domain.spi;

import com.pragma.powerup.plazamicroservice.adapters.driven.jpa.mysql.feign.dto.UserDto;

public interface IUserServicePort {
    UserDto getUser(Long id, String token);
}
