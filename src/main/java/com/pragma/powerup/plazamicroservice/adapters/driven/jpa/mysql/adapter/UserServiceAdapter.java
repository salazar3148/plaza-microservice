package com.pragma.powerup.plazamicroservice.adapters.driven.jpa.mysql.adapter;

import com.pragma.powerup.plazamicroservice.adapters.driven.jpa.mysql.feign.client.UserFeignClient;
import com.pragma.powerup.plazamicroservice.adapters.driven.jpa.mysql.mappers.IUserMapper;
import com.pragma.powerup.plazamicroservice.domain.model.User;
import com.pragma.powerup.plazamicroservice.domain.spi.IUserServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
public class UserServiceAdapter implements IUserServicePort {

    private final UserFeignClient userFeignClient;
    private final IUserMapper userMapper;

    @Override
    public User getUser(String token) {
        return userMapper.userDtoToUser(userFeignClient.getUser(token));
    }

    @Override
    public User getUserById(String token, Long id) {
        return userMapper.userDtoToUser(userFeignClient.getUserById(token, id));
    }
}
