package ru.vote.system.restaurant.repository;

import ru.vote.system.restaurant.model.Vote;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface VoteRepository {
    // null if not updated
    Vote update(Vote vote, int restId, LocalDateTime dateTime);

    // false if not deleted
    boolean delete(int userId, LocalDate date);

    // null if user haven't placed a vote today
    Vote get(LocalDate date, int userId);

    // ORDERED date
    List<Vote> getAll(int userId);

    //null if not created
    Vote create(int userId, int restId, LocalDateTime dateTime);
}
