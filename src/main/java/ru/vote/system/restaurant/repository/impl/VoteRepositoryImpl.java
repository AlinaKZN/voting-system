package ru.vote.system.restaurant.repository.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.vote.system.restaurant.model.Vote;
import ru.vote.system.restaurant.repository.CrudUserRepository;
import ru.vote.system.restaurant.repository.CrudVoteRepository;
import ru.vote.system.restaurant.repository.VoteRepository;

import java.util.List;

@Repository
public class VoteRepositoryImpl implements VoteRepository {

    CrudVoteRepository voteRepository;
    CrudUserRepository userRepository;

    public VoteRepositoryImpl(CrudVoteRepository voteRepository, CrudUserRepository userRepository) {
        this.voteRepository = voteRepository;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public Vote save(Vote vote, int userId) {
        if (!vote.isNew() && get(vote.id(), userId) == null) {
            return null;
        }
        vote.setUser(userRepository.getOne(userId));
        return voteRepository.save(vote);
    }

    @Override
    public boolean delete(int id, int userId) {
        return voteRepository.delete(id, userId) != 0;
    }

    @Override
    public Vote get(int id, int userId) {
        return voteRepository.findById(id).filter(vote -> vote.getUser().getId() == userId).orElse(null);
    }

    @Override
    public List<Vote> getAll(int userId) {
        return voteRepository.getAll(userId);
    }

}
