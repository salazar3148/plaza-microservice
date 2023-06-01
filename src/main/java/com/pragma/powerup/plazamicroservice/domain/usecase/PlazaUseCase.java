package com.pragma.powerup.plazamicroservice.domain.usecase;
import com.pragma.powerup.plazamicroservice.domain.api.IPlazaServicePort;
import com.pragma.powerup.plazamicroservice.domain.exceptions.InvalidRoleAssignmentException;
import com.pragma.powerup.plazamicroservice.domain.exceptions.PlazaAlreadyExistsException;
import com.pragma.powerup.plazamicroservice.domain.exceptions.UnauthorizedException;
import com.pragma.powerup.plazamicroservice.domain.model.Plaza;
import com.pragma.powerup.plazamicroservice.domain.model.User;
import com.pragma.powerup.plazamicroservice.domain.spi.IPlazaPersistencePort;
import com.pragma.powerup.plazamicroservice.domain.spi.IUserServicePort;
import org.springframework.data.domain.Page;

import static com.pragma.powerup.plazamicroservice.configuration.Constants.ADMIN_ROLE_ID;
import static com.pragma.powerup.plazamicroservice.configuration.Constants.CUSTOMER_ROLE_ID;
import static com.pragma.powerup.plazamicroservice.configuration.Constants.OWNER_ROLE_ID;

public class PlazaUseCase implements IPlazaServicePort {
    private final IPlazaPersistencePort plazaPersistencePort;
    private final IUserServicePort userServicePort;

    public PlazaUseCase(IPlazaPersistencePort plazaPersistencePort, IUserServicePort userServicePort) {
        this.plazaPersistencePort = plazaPersistencePort;
        this.userServicePort = userServicePort;
    }

    @Override
    public void savePlaza(Plaza plaza, String token) {
        User adminUser = userServicePort.getUser(token);
        User ownerUser = userServicePort.getUserById(token, plaza.getIdOwner());

        if(plazaPersistencePort.existsByNit(plaza.getNit())){
            throw new PlazaAlreadyExistsException();
        }

        if(!adminUser.getIdRole().equals(ADMIN_ROLE_ID)){
            throw new UnauthorizedException();
        }

        if(!ownerUser.getIdRole().equals(OWNER_ROLE_ID)) {
            throw new InvalidRoleAssignmentException();
        }

        plaza.setIdOwner(ownerUser.getId());

        plazaPersistencePort.savePlaza(plaza);
    }

    @Override
    public Page<Plaza> getAllPlazaFilter(String token, int minPage, int sizePage) {

        User customerUser = userServicePort.getUser(token);

        if(!customerUser.getIdRole().equals(CUSTOMER_ROLE_ID)){
            throw new UnauthorizedException();
        }

        return plazaPersistencePort.getPlazaList(minPage, sizePage);
    }
}
