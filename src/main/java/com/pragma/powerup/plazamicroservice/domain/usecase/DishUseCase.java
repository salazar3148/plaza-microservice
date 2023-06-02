package com.pragma.powerup.plazamicroservice.domain.usecase;
import com.pragma.powerup.plazamicroservice.domain.api.IDishServicePort;
import com.pragma.powerup.plazamicroservice.domain.exceptions.DomainCategoryNotFoundException;
import com.pragma.powerup.plazamicroservice.domain.exceptions.DomainPlazaNotFoundException;
import com.pragma.powerup.plazamicroservice.domain.exceptions.UnauthorizedException;
import com.pragma.powerup.plazamicroservice.domain.exceptions.UnauthorizedRestaurantAccessException;
import com.pragma.powerup.plazamicroservice.domain.model.Dish;
import com.pragma.powerup.plazamicroservice.domain.model.User;
import com.pragma.powerup.plazamicroservice.domain.spi.ICategoryPersistencePort;
import com.pragma.powerup.plazamicroservice.domain.spi.IDishPersistencePort;
import com.pragma.powerup.plazamicroservice.domain.spi.IPlazaPersistencePort;
import com.pragma.powerup.plazamicroservice.domain.spi.IUserServicePort;
import org.springframework.data.domain.Page;

import static com.pragma.powerup.plazamicroservice.configuration.Constants.CUSTOMER_ROLE_ID;
import static com.pragma.powerup.plazamicroservice.configuration.Constants.OWNER_ROLE_ID;

public class DishUseCase implements IDishServicePort {
    IDishPersistencePort dishPersistencePort;
    ICategoryPersistencePort categoryPersistencePort;
    IPlazaPersistencePort plazaPersistencePort;
    IUserServicePort userServicePort;

    public DishUseCase(IDishPersistencePort dishPersistencePort, ICategoryPersistencePort categoryPersistencePort, IPlazaPersistencePort plazaPersistencePort, IUserServicePort userServicePort) {
        this.dishPersistencePort = dishPersistencePort;
        this.categoryPersistencePort = categoryPersistencePort;
        this.plazaPersistencePort = plazaPersistencePort;
        this.userServicePort = userServicePort;
    }

    @Override
    public void saveDish(String token, Dish dish) {
        User owner = userServicePort.getUser(token);

        if(!owner.getIdRole().equals(OWNER_ROLE_ID)){
            throw new UnauthorizedException();
        }

        if(!plazaPersistencePort.existsByIdOwnerAndId(owner.getId(), dish.getPlaza().getId())){
            throw new UnauthorizedRestaurantAccessException();
        }

        if(!categoryPersistencePort.existsById(dish.getCategory().getId())){
            throw new DomainCategoryNotFoundException();
        }

        if(!plazaPersistencePort.existById(dish.getPlaza().getId())){
            throw new DomainPlazaNotFoundException();
        }

        dish.setStatus(true);
        dishPersistencePort.saveDish(dish);
    }

    @Override
    public void updateDish(String token, Long id, Dish dish) {
        User owner = userServicePort.getUser(token);

        if(!owner.getIdRole().equals(OWNER_ROLE_ID)){
            throw new UnauthorizedException();
        }

        Dish oldDish = dishPersistencePort.getDish(id);

        if(!plazaPersistencePort.existsByIdOwnerAndId(owner.getId(), oldDish.getPlaza().getId())){
            throw new UnauthorizedRestaurantAccessException();
        }

        if(dish.getPrice() != null){
            oldDish.setPrice(dish.getPrice());
        }

        if(dish.getDescription() != null){
            oldDish.setDescription(dish.getDescription());
        }

        dishPersistencePort.saveDish(oldDish);
    }

    @Override
    public void updateStatusDish(String token, Long id) {
        User owner = userServicePort.getUser(token);

        if(!owner.getIdRole().equals(OWNER_ROLE_ID)){
            throw new UnauthorizedException();
        }

        Dish dish = dishPersistencePort.getDish(id);

        if(!plazaPersistencePort.existsByIdOwnerAndId(owner.getId(), dish.getPlaza().getId())){
            throw new UnauthorizedRestaurantAccessException();
        }

        dish.setStatus(!dish.getStatus());

        dishPersistencePort.saveDish(dish);
    }

    @Override
    public Page<Dish> getPageDish(String token, String categoryName, Integer numPage, Integer sizePage) {
        User customerUser = userServicePort.getUser(token);

        if(!customerUser.getIdRole().equals(CUSTOMER_ROLE_ID)){
            throw new UnauthorizedException();
        }

        return dishPersistencePort.getDishPage(categoryName, numPage, sizePage);
    }
}
