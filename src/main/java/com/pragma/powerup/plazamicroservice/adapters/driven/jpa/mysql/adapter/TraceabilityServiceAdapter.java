package com.pragma.powerup.plazamicroservice.adapters.driven.jpa.mysql.adapter;

import com.pragma.powerup.plazamicroservice.adapters.driven.jpa.mysql.feign.client.TraceabilityFeignClient;
import com.pragma.powerup.plazamicroservice.adapters.driven.jpa.mysql.mappers.ITraceabilityRequestMapper;
import com.pragma.powerup.plazamicroservice.domain.model.traceability.Traceability;
import com.pragma.powerup.plazamicroservice.domain.spi.ITraceabilityServicePort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TraceabilityServiceAdapter implements ITraceabilityServicePort {

    private final TraceabilityFeignClient traceabilityFeignClient;
    private final ITraceabilityRequestMapper traceabilityRequestMapper;
    @Override
    public void saveTraceability(Traceability traceability) {
        traceabilityFeignClient.saveTraceability(
                traceabilityRequestMapper.traceabilityToTraceabilityRequestDto(traceability)
        );
    }
}
