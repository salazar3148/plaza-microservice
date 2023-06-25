package com.pragma.powerup.plazamicroservice.adapters.driven.jpa.mysql.feign.client;


import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@Headers("Content-Type: application/json")
@FeignClient(name = "messaging-microservice", url = "http://localhost:8092/sms")
public interface MessagingFeignClient {
    @PostMapping
    String sendVerificationCode(String phoneNumber);

}
