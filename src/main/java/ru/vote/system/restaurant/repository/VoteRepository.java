package ru.vote.system.restaurant.repository;

import ru.vote.system.restaurant.model.Vote;

import java.time.LocalDate;
import java.util.List;

public interface VoteRepository {
    // null if updated vote do not belong to userId
    Vote save(Vote vote, int userId);

    // false if vote do not belong to userId
    boolean delete(int id, int userId);

    // null if vote do not belong to userId
    Vote get(int id, int userId);

    // ORDERED dateTime desc
    List<Vote> getAll(int userId);

    int getRestaurantVotesCount(Integer restId, LocalDate date);

    Vote getVoteByUserIdAndDate(Integer userId, LocalDate date);
}
