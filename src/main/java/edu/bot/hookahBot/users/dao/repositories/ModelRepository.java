package edu.bot.hookahBot.users.dao.repositories;

import java.util.List;
import java.util.Optional;

public interface ModelRepository<T> {
    List<T> getAll();
    void update(T entity);
    Optional<T> get(long id);
    void save(T entity);
    void delete(T entity);
}