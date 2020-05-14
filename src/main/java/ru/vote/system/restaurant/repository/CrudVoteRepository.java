package ru.vote.system.restaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.vote.system.restaurant.model.Vote;

import java.time.LocalDateTime;
import java.util.List;

@Transactional(readOnly = true)
public interface CrudVoteRepository extends JpaRepository<Vote, Integer> {

    @Modifying
    @Transactional
    @Query("DELETE FROM Vote v WHERE v.user.id=:userId AND v.placed>=:startOfDay AND v.placed<:startOfNextDay")
    int delete(@Param("userId") int userId, @Param("startOfDay") LocalDateTime startOfDay, @Param("startOfNextDay") LocalDateTime startOfNextDay);

    @Query("SELECT v FROM Vote v WHERE v.user.id=:userId ORDER BY v.placed")
    List<Vote> getAll(@Param("userId") int userId);

    @Query("SELECT v FROM Vote v WHERE v.user.id=:userId AND v.placed>=:startOfDay AND v.placed<:startOfNextDay")
    Vote get(@Param("userId") int userId, @Param("startOfDay") LocalDateTime startOfDay, @Param("startOfNextDay") LocalDateTime startOfNextDay);

    @Query("SELECT v FROM Vote v WHERE v.restaurant.id=:restId AND v.placed >= :startOfDay AND v.placed < :startOfNextDay")
    List<Vote> getVotesByRestaurantAndDate(@Param("restId") int restId, @Param("startOfDay") LocalDateTime startOfDay, @Param("startOfNextDay") LocalDateTime startOfNextDay);
}
