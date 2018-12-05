package edu.bot.hookahBot.orders.repository;

import edu.bot.hookahBot.managers.model.Manager;
import edu.bot.hookahBot.orders.model.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.UUID;

@Transactional
@Repository
public interface OrderRepo extends CrudRepository<Order, Long> {}
