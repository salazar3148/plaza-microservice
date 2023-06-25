package com.pragma.powerup.plazamicroservice.adapters.driven.jpa.mysql.adapter;

import com.pragma.powerup.plazamicroservice.adapters.driven.jpa.mysql.feign.client.MessagingFeignClient;
import com.pragma.powerup.plazamicroservice.domain.spi.ISmsServicePort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SmsServiceAdapter implements ISmsServicePort {

    private final MessagingFeignClient messagingFeignClient;
    @Override
    public String sendVerificationCode(String phoneNumber) {
        return messagingFeignClient.sendVerificationCode(phoneNumber);
    }
}
