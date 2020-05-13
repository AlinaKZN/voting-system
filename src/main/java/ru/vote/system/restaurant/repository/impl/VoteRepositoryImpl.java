package ru.vote.system.restaurant.repository.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.vote.system.restaurant.model.Restaurant;
import ru.vote.system.restaurant.model.User;
import ru.vote.system.restaurant.model.Vote;
import ru.vote.system.restaurant.repository.CrudVoteRepository;
import ru.vote.system.restaurant.repository.VoteRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Repository
public class VoteRepositoryImpl implements VoteRepository {

    CrudVoteRepository voteRepository;

    @PersistenceContext
    private EntityManager em;

    public VoteRepositoryImpl(CrudVoteRepository voteRepository) {
        this.voteRepository = voteRepository;
    }

    @Override
    @Transactional
    public Vote update(Vote vote, int restId, LocalDateTime dateTime) {
        Restaurant restaurant = em.getReference(Restaurant.class, restId);
        vote.setRestaurant(restaurant);
        vote.setPlaced(dateTime);
        return voteRepository.save(vote);
    }

    @Override
    public boolean delete(int userId, LocalDate date) {
        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime startOfNextDay = date.plus(1, ChronoUnit.DAYS).atStartOfDay();
        return voteRepository.delete(userId, startOfDay, startOfNextDay) != 0;
    }

    @Override
    public Vote get(LocalDate date, int userId) {
        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime startOfNextDay = date.plus(1, ChronoUnit.DAYS).atStartOfDay();
        return voteRepository.get(userId, startOfDay, startOfNextDay);
    }

    @Override
    public List<Vote> getAll(int userId) {
        return voteRepository.getAll(userId);
    }

    @Override
    public Vote create(int userId, int restId, LocalDateTime dateTime) {
        User user = em.getReference(User.class, userId);
        Restaurant restaurant = em.getReference(Restaurant.class, restId);
        Vote vote = new Vote(dateTime, restaurant, user);
        return voteRepository.save(vote);
    }

}
