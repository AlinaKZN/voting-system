package ru.vote.system.restaurant.repository;

import ru.vote.system.restaurant.model.Vote;

import java.util.List;

public interface VoteRepository {
    // null if updated vote do not belong to userId
    Vote save(Vote vote, int userId);

    // false if vote do not belong to userId
    boolean delete(int id, int userId);

    // null if vote do not belong to userId
    Vote get(int id, int userId);

    // ORDERED date
    List<Vote> getAll(int userId);

}
