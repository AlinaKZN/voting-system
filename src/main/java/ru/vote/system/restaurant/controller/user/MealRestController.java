package ru.vote.system.restaurant.controller.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.vote.system.restaurant.model.Meal;
import ru.vote.system.restaurant.service.MealService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = MealRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class MealRestController {
    private Logger log = LoggerFactory.getLogger(MealRestController.class);
    protected static final String REST_URL = "/rest/profile/menu";

    @Autowired
    MealService service;

    @GetMapping(value = "/{id}")
    public List<Meal> getMenu(@PathVariable int id) {
        log.info("menu of the day for restaurant {} and date {}", id, LocalDate.now());
        return service.getMenu(id, LocalDate.now());
    }

    @GetMapping(value = "/{id}/history")
    public List<Meal> getMealsHistory(@PathVariable int id) {
        log.info("meals history for restaurant {}", id);
        return service.getAll(id);
    }

}