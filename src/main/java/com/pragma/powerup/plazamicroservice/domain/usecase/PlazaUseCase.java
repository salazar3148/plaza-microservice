package com.pragma.powerup.plazamicroservice.domain.usecase;

import com.pragma.powerup.plazamicroservice.configuration.Constants;
import com.pragma.powerup.plazamicroservice.domain.api.IPlazaServicePort;
import com.pragma.powerup.plazamicroservice.domain.model.Plaza;
import com.pragma.powerup.plazamicroservice.domain.spi.IPlazaPersistencePort;
import com.pragma.powerup.plazamicroservice.domain.spi.IUserServicePort;

public class PlazaUseCase implements IPlazaServicePort {
    private final IPlazaPersistencePort plazaPersistencePort;
    private final IUserServicePort userServicePort;

    public PlazaUseCase(IPlazaPersistencePort plazaPersistencePort, IUserServicePort userServicePort) {
        this.plazaPersistencePort = plazaPersistencePort;
        this.userServicePort = userServicePort;
    }

    @Override
    public void savePlaza(Plaza plaza) {
        if(userServicePort.getUser(plaza.getId_propietario(),"Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxMjMiLCJyb2xlcyI6WyJST0xFX0FETUlOIl0sImlhdCI6MTY4NDIyNTAwNSwiZXhwIjoxNjg0ODczMDA1fQ.pYUdu6E1TJSpJ0l79WfQTGcHnADgd3DJflries23LVc").getId_role().getId() != Constants.PROVIDER_ROLE_ID) {
            throw new RuntimeException("ASDASDA");
        }
        plazaPersistencePort.savePlaza(plaza);
    }
}
