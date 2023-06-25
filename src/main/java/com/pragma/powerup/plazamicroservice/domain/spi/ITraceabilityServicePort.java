package com.pragma.powerup.plazamicroservice.domain.spi;

import com.pragma.powerup.plazamicroservice.domain.model.traceability.Traceability;

public interface ITraceabilityServicePort {
    void saveTraceability(Traceability traceability);;
}
