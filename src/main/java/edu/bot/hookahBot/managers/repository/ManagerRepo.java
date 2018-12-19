package edu.bot.hookahBot.managers.repository;

import edu.bot.hookahBot.managers.model.Manager;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.UUID;

@Transactional
@Repository
public interface ManagerRepo extends CrudRepository<Manager, Long> {}
