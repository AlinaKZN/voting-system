package ru.vote.system.restaurant.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.vote.system.restaurant.model.Meal;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MealRepository extends CrudRepository<Meal, Integer> {

    @Query("SELECT m FROM Meal m WHERE m.restaurant.id=:restId AND m.date=:date")
    List<Meal> getMenu(int restId, LocalDate date);

    @Query("SELECT m FROM Meal m WHERE m.restaurant.id=:restId ORDER BY m.date")
    List<Meal> getAll(int id);
}
