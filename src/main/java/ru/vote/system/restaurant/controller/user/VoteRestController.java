package ru.vote.system.restaurant.controller.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.vote.system.restaurant.model.Vote;
import ru.vote.system.restaurant.service.VoteService;
import ru.vote.system.restaurant.web.SecurityUtil;

import java.net.URI;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static ru.vote.system.restaurant.util.DateUtil.isLate;

@RestController
@RequestMapping(value = VoteRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class VoteRestController {
    private final Logger log = LoggerFactory.getLogger(getClass());
    protected static final String REST_URL = "/rest/profile/vote";

    @Autowired
    VoteService service;

    @PostMapping(value = "/{restId}")
    public ResponseEntity<Vote> vote(@PathVariable int restId) {
        int userId = SecurityUtil.authUserId();
        LocalDateTime dateTime = LocalDateTime.now();
        Vote vote = service.get(dateTime.toLocalDate(), userId);
        if (vote == null) {
            log.info("create vote from user {} for restaurant {}", userId, restId);
            vote = service.create(userId, restId, dateTime);

            URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path(REST_URL + "/{id}")
                    .buildAndExpand(vote.getId()).toUri();
            return ResponseEntity.created(uriOfNewResource).body(vote);
        }

        if (isLate(dateTime)) {
            log.info("too late to vote at {}", dateTime);
            return ResponseEntity.badRequest().build();
        }
        log.info("update vote from user {} for restaurant {}", userId, restId);
        vote = service.update(vote, restId, dateTime);
        return (vote == null) ? ResponseEntity.badRequest().build() : ResponseEntity.noContent().build();
    }

    @GetMapping
    public Vote get() {
        int userId = SecurityUtil.authUserId();
        LocalDate date = LocalDate.now();
        log.info("get today vote for user {}", userId);
        return service.get(date, userId);
    }

    @DeleteMapping
    public ResponseEntity<Void> delete() {
        int userId = SecurityUtil.authUserId();
        log.info("delete today vote for user {}", userId);
        LocalDateTime dateTime = LocalDateTime.now();
        if (isLate(dateTime) || !service.delete(userId, dateTime.toLocalDate())) {
            return ResponseEntity.badRequest().build();
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/history")
    public List<Vote> getAll() {
        int userId = SecurityUtil.authUserId();
        log.info("history of votes for user {}", userId);
        return service.getAll(userId);
    }
}
