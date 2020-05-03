package ru.vote.system.restaurant.repository.impl;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import ru.vote.system.restaurant.model.User;
import ru.vote.system.restaurant.repository.CrudUserRepository;

import java.util.List;

@Repository
public class UserRepositoryImpl {
    private static final Sort SORT_NAME_EMAIL = Sort.by(Sort.Direction.ASC, "name", "email");

    private final CrudUserRepository crudRepository;

    public UserRepositoryImpl(CrudUserRepository crudRepository) {
        this.crudRepository = crudRepository;
    }

    public User save(User user) {
        return crudRepository.save(user);
    }

    public boolean delete(int id) {
        return crudRepository.delete(id) != 0;
    }

    public User get(int id) {
        return crudRepository.findById(id).orElse(null);
    }

    public User getByEmail(String email) {
        return crudRepository.getByEmail(email);
    }

    public List<User> getAll() {
        return crudRepository.findAll(SORT_NAME_EMAIL);
    }

    public User getWithVotes(int id) {
        return crudRepository.getWithVotes(id);
    }
}
