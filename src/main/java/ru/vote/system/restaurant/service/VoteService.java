package ru.vote.system.restaurant.service;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.vote.system.restaurant.model.Vote;
import ru.vote.system.restaurant.repository.VoteRepository;
import ru.vote.system.restaurant.util.DateUtil;

import java.time.LocalDate;

@Service
public class VoteService {

    VoteRepository repository;

    public VoteService(VoteRepository repository) {
        this.repository = repository;
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

    public int getRestaurantVotesCount(int restId, LocalDate date) {
        return repository.getRestaurantVotesCount(restId, date);
    }

    public Vote getVoteByUserAndDate(int userId, LocalDate date) {
        return repository.getVoteByUserIdAndDate(userId, date);
    }
}
