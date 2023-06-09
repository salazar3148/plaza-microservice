package com.pragma.powerup.plazamicroservice.domain.spi;


import com.pragma.powerup.plazamicroservice.domain.model.user.User;

public interface IUserServicePort {
    User getUser(String token);
    User getUserById(String token, Long id);
}
