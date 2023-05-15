package com.pragma.powerup.plazamicroservice.domain.usecase;

import com.pragma.powerup.plazamicroservice.domain.api.IPlazaServicePort;
import com.pragma.powerup.plazamicroservice.domain.model.Plaza;
import com.pragma.powerup.plazamicroservice.domain.spi.IPlazaPersistencePort;

public class PlazaUseCase implements IPlazaServicePort {
    private final IPlazaPersistencePort plazaPersistencePort;

    public PlazaUseCase(IPlazaPersistencePort personPersistencePort) {
        this.plazaPersistencePort = personPersistencePort;
    }

    @Override
    public void savePlaza(Plaza plaza) {
        plazaPersistencePort.savePlaza(plaza);
    }
}
