package ru.vote.system.restaurant.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.vote.system.restaurant.model.Vote;

import java.time.LocalDate;
import java.util.List;

public interface CrudVoteRepository extends CrudRepository<Vote, Integer> {

    @Query("SELECT v FROM Vote v WHERE v.restaurant.id = :restId and v.placed = :date")
    List<Vote> getVotesByRestaurant(Integer restId, LocalDate date);

    //  @Query("SELECT v FROM Vote v WHERE v.user.id = :userId")
    List<Vote> getVotesByUserId(Integer userId);

    @Query("SELECT v FROM Vote v WHERE v.user.id = :userId and v.placed = :date")
    Vote getVoteByUserAndDate(Integer userId, LocalDate date);
}
