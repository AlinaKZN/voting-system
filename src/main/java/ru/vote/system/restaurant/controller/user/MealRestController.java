package ru.vote.system.restaurant.controller.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.vote.system.restaurant.model.Meal;
import ru.vote.system.restaurant.service.MealService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = MealRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class MealRestController {
    private Logger log = LoggerFactory.getLogger(MealRestController.class);
    protected static final String REST_URL = "/rest/profile/meals";

    @Autowired
    MealService service;

    @GetMapping
    public List<Meal> getMenu(@RequestParam int id, @RequestParam LocalDate date) {
        log.info("menu of the day for restaurant {} and date {}", id, date);
        return service.getMenu(id, date);
    }

    @GetMapping(value = "/{id}")
    public List<Meal> getMealsHistory(@PathVariable int id) {
        log.info("meals history for restaurant {}", id);
        return service.getAll(id);
    }

}