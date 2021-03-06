package ru.vote.system.restaurant.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.vote.system.restaurant.model.Restaurant;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface CrudRestaurantRepository extends CrudRepository<Restaurant, Integer> {

    @Query("SELECT r FROM Restaurant r")
    List<Restaurant> findAll();

    // @EntityGraph(attributePaths = {"menu"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT DISTINCT r FROM Restaurant r JOIN FETCH r.menu m where m.date =?1")
    List<Restaurant> findAllWithMenuOfDay(LocalDate date);

    @Query("SELECT DISTINCT r FROM Restaurant r JOIN FETCH r.votes v where v.placed>=:startOfDay AND v.placed<:startOfNextDay")
    List<Restaurant> findAllWithVotesOfDay(@Param("startOfDay") LocalDateTime startOfDay, @Param("startOfNextDay") LocalDateTime startOfNextDay);

    //votes history
    @EntityGraph(attributePaths = {"votes"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT r FROM Restaurant r WHERE r.id=?1")
    Restaurant getWithVotes(int id);

    //menu history
    @EntityGraph(attributePaths = {"menu"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT r FROM Restaurant r WHERE r.id=?1")
    Restaurant getWithMenu(int id);
}
