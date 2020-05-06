package ru.vote.system.restaurant.controller.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.vote.system.restaurant.model.Meal;
import ru.vote.system.restaurant.service.MealService;

import java.net.URI;

import static ru.vote.system.restaurant.util.ValidationUtil.assureIdConsistent;
import static ru.vote.system.restaurant.util.ValidationUtil.checkNew;

@RestController
@RequestMapping(value = MealAdminRestController.REST_URL)
public class MealAdminRestController {
    private Logger log = LoggerFactory.getLogger(MealAdminRestController.class);
    protected static final String REST_URL = "/rest/admin/meals";

    @Autowired
    private MealService service;

    @GetMapping("/${id}")
    public Meal get(@PathVariable int id) {
        log.info("get meal {}", id);
        return service.get(id);
    }

    @DeleteMapping(value = "/${id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        log.info("delete meal {}", id);
        service.delete(id);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public Meal update(@RequestBody Meal meal, @PathVariable int id) {
        assureIdConsistent(meal, id);
        log.info("update {} ", meal);
        return service.save(meal);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Meal> createWithLocation(@RequestBody Meal meal) {
        checkNew(meal);
        log.info("create {} ", meal);
        Meal created = service.save(meal);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }
}
