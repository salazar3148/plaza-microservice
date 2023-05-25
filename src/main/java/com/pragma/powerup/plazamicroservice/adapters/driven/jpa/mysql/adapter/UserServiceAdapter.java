package com.pragma.powerup.plazamicroservice.adapters.driven.jpa.mysql.adapter;

import com.pragma.powerup.plazamicroservice.adapters.driven.jpa.mysql.feign.client.UserFeignClient;
import com.pragma.powerup.plazamicroservice.adapters.driven.jpa.mysql.feign.dto.UserDto;
import com.pragma.powerup.plazamicroservice.domain.spi.IUserServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceAdapter implements IUserServicePort {

    private final UserFeignClient userFeignClient;

    @Override
    public UserDto getUser(Long id, String token) {
        return userFeignClient.getUser(id, token);
    }
}
