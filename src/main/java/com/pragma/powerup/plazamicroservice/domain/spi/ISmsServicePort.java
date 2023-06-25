package com.pragma.powerup.plazamicroservice.domain.spi;

public interface ISmsServicePort {
    String sendVerificationCode(String phoneNumber);
}
