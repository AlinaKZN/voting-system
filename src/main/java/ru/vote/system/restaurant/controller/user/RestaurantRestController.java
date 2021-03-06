package ru.vote.system.restaurant.controller.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.vote.system.restaurant.model.Restaurant;
import ru.vote.system.restaurant.service.RestaurantService;

import java.util.List;

@RestController
@RequestMapping(value = RestaurantRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class RestaurantRestController {
    private static final Logger log = LoggerFactory.getLogger(RestaurantRestController.class);
    static final String REST_URL = "/rest/profile/restaurants";

    @Autowired
    private RestaurantService service;

    @GetMapping("/menus")
    public List<Restaurant> getAllWithTodayMenu() {
        log.info("getAll restaurants with today menu");
        return service.getAllWithTodayMenu();
    }

    @GetMapping("/votes")
    public List<Restaurant> getAllWithTodayVotes() {
        log.info("getAll restaurants with today votes");
        return service.getAllWithTodayVotes();
    }

    @GetMapping
    public List<Restaurant> getAll() {
        log.info("getAll restaurants");
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Restaurant get(@PathVariable int id) {
        log.info("get {}", id);
        return service.get(id).isPresent() ? service.get(id).get() : null;
    }

    @GetMapping("/{id}/menu")
    public Restaurant getWithMenu(@PathVariable int id) {
        log.info("get restaurant id= {} with menu", id);
        return service.getWithMenu(id);
    }

    @GetMapping("/{id}/votes")
    public Restaurant getWithVotes(@PathVariable int id) {
        log.info("get restaurant {} with votes", id);
        return service.getWithVotes(id);
    }
}
