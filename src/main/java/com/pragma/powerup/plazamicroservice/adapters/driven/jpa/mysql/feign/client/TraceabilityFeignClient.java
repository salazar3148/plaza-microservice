package com.pragma.powerup.plazamicroservice.adapters.driven.jpa.mysql.feign.client;

import com.pragma.powerup.plazamicroservice.adapters.driven.jpa.mysql.feign.dto.TraceabilityRequestDto;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@Headers("Content-Type: application/json")
@FeignClient(name = "traceability-microservice", url = "http://localhost:8093/traceability")
public interface TraceabilityFeignClient {
    @PostMapping
    void saveTraceability(TraceabilityRequestDto traceabilityRequestDto);
}
