package ru.vote.system.restaurant.service;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.vote.system.restaurant.model.Meal;
import ru.vote.system.restaurant.repository.MealRepository;

import java.time.LocalDate;
import java.util.List;

import static ru.vote.system.restaurant.util.ValidationUtil.checkNotFoundWithId;

@Service
public class MealService {

    private final MealRepository repository;

    public MealService(MealRepository repository) {
        this.repository = repository;
    }

    public Meal get(int id) {
        return checkNotFoundWithId(repository.findById(id), id);
    }

    public void delete(int id) {
        repository.deleteById(id);
    }

    public List<Meal> getAll(int id) {
        return repository.getAll(id);
    }

    public Meal save(Meal meal) {
        Assert.notNull(meal, "meal must not be null");
        return repository.save(meal);
    }

    public List<Meal> getMenu(int restId, LocalDate date) {
        return repository.getMenu(restId, date);
    }
}
