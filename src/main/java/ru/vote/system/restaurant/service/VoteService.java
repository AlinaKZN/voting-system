package ru.vote.system.restaurant.service;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.vote.system.restaurant.model.Vote;
import ru.vote.system.restaurant.repository.VoteRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class VoteService {

    VoteRepository repository;

    public VoteService(VoteRepository repository) {
        this.repository = repository;
    }

    public Vote get(LocalDate date, int userId) {
        return repository.get(date, userId);
    }

    public List<Vote> getAll(int userId) {
        return repository.getAll(userId);
    }

    public Vote create(int userId, int restId, LocalDateTime dateTime) {
        return repository.create(userId, restId, dateTime);
    }

    public Vote update(Vote vote, Integer restId, LocalDateTime dateTime) {
        Assert.notNull(vote, "vote must not be null");
        return repository.update(vote, restId, dateTime);
    }

    public boolean delete(int userId, LocalDate date) {
        return repository.delete(userId, date);
    }
}
