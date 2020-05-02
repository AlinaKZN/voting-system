package ru.vote.system.restaurant.repository;

import org.springframework.data.repository.CrudRepository;
import ru.vote.system.restaurant.model.Meal;

public interface CrudRestaurantRepository extends CrudRepository<Meal, Integer> {

}
