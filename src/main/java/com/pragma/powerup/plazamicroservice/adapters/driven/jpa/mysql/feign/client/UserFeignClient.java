package com.pragma.powerup.plazamicroservice.adapters.driven.jpa.mysql.feign.client;

import com.pragma.powerup.plazamicroservice.adapters.driven.jpa.mysql.feign.dto.UserDto;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@Headers("Content-Type: application/json")
@FeignClient(name = "user-microservice", url = "http://localhost:8090/users/")
public interface UserFeignClient {

    @GetMapping
    UserDto getUser(@RequestHeader("Authorization") String token);

    @GetMapping("/{id}")
    UserDto getUserById(@RequestHeader("Authorization") String token, @PathVariable Long id);
}
