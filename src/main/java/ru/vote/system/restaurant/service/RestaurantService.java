package ru.vote.system.restaurant.service;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.vote.system.restaurant.model.Restaurant;
import ru.vote.system.restaurant.repository.CrudRestaurantRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
public class RestaurantService {

    CrudRestaurantRepository repository;

    public RestaurantService(CrudRestaurantRepository repository) {
        this.repository = repository;
    }

    public Optional<Restaurant> get(Integer id) {
        return repository.findById(id);
    }

    public void delete(int id) {
        repository.deleteById(id);
    }

    public List<Restaurant> getAll() {
        return repository.findAll();
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

    public List<Restaurant> getAllWithTodayMenu() {
        return repository.findAllWithMenuOfDay(LocalDate.now());
    }

    public List<Restaurant> getAllWithTodayVotes() {
        LocalDate date = LocalDate.now();
        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime startOfNextDay = date.plus(1, ChronoUnit.DAYS).atStartOfDay();
        return repository.findAllWithVotesOfDay(startOfDay, startOfNextDay);
    }

}
