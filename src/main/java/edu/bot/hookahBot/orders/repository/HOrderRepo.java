package edu.bot.hookahBot.orders.repository;

import edu.bot.hookahBot.orders.model.HOrder;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface HOrderRepo extends CrudRepository<HOrder, Long> {}
