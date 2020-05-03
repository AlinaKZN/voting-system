package ru.vote.system.restaurant.service;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.vote.system.restaurant.model.Restaurant;
import ru.vote.system.restaurant.repository.RestaurantRepository;

import java.util.List;

import static ru.vote.system.restaurant.util.ValidationUtil.checkNotFoundWithId;

@Service
public class RestaurantService {

    RestaurantRepository repository;

    public RestaurantService(RestaurantRepository repository) {
        this.repository = repository;
    }

    public Restaurant get(Integer id) {
        return checkNotFoundWithId(repository.findById(id), id);
    }

    public void delete(int id) {
        repository.deleteById(id);
    }

    public List<Restaurant> getAll() {
        return repository.getRestaurants();
    }

    public void update(Restaurant restaurant) {
        Assert.notNull(restaurant, "meal must not be null");
        repository.save(restaurant);
    }

    public Restaurant getWithVotes(int id) {
        return repository.getWithVotes(id);
    }

    public Restaurant getWithMenu(int id) {
        return repository.getWithMenu(id);
    }


}
