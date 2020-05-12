package ru.vote.system.restaurant.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;
import ru.vote.system.restaurant.model.Meal;

import java.time.LocalDate;
import java.util.List;

public interface MealRepository extends CrudRepository<Meal, Integer> {

    @Transactional
    @Query("SELECT m FROM Meal m WHERE m.restaurant.id=?1 AND m.date=?2")
    List<Meal> getMenu(int restId, LocalDate date);

    @Transactional
    @Query("SELECT m FROM Meal m WHERE m.restaurant.id=?1 ORDER BY m.date")
    List<Meal> getAll(int id);
}
