package ru.vote.system.restaurant.service;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.vote.system.restaurant.model.Vote;
import ru.vote.system.restaurant.repository.VoteRepository;
import ru.vote.system.restaurant.util.DateUtil;

import java.util.List;

import static ru.vote.system.restaurant.util.ValidationUtil.checkNotFoundWithId;

@Service
public class VoteService {

    VoteRepository repository;

    public VoteService(VoteRepository repository) {
        this.repository = repository;
    }

    public Vote get(int id, int userId) {
        return repository.get(id, userId);
    }

    public List<Vote> getAll(int userId) {
        return repository.getAll(userId);
    }

    public Vote create(Vote vote, int userId) {
        Assert.notNull(vote, "vote must not be null");
        return repository.save(vote, userId);
    }

    public Vote update(Vote vote, Integer userId) {
        Assert.notNull(vote, "vote must not be null");
        if (vote.getPlaced().toLocalTime().isBefore(DateUtil.STOP_TIME)) {
            return repository.save(vote, userId);
        } else {
            throw new RuntimeException("Too late to vote, try tomorrow");
        }
    }
    
    public void delete(int id, int userId) {
        checkNotFoundWithId(repository.delete(id, userId), id);
    }
}
