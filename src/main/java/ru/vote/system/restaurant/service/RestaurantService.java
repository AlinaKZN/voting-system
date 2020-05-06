package ru.vote.system.restaurant.service;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.vote.system.restaurant.model.Restaurant;
import ru.vote.system.restaurant.repository.RestaurantRepository;

import java.util.List;
import java.util.Optional;

@Service
public class RestaurantService {

    RestaurantRepository repository;

    public RestaurantService(RestaurantRepository repository) {
        this.repository = repository;
    }

    public Optional<Restaurant> get(Integer id) {
        return repository.findById(id);
    }

    public void delete(int id) {
        repository.deleteById(id);
    }

    public List<Restaurant> getAll() {
        return repository.getRestaurants();
    }

    public Restaurant save(Restaurant restaurant) {
        Assert.notNull(restaurant, "restaurant must not be null");
        return repository.save(restaurant);
    }

    public Restaurant getWithVotes(int id) {
        return repository.getWithVotes(id);
    }

    public Restaurant getWithMenu(int id) {
        return repository.getWithMenu(id);
    }


}
