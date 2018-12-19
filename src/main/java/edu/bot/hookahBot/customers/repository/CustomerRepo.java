package edu.bot.hookahBot.customers.repository;

import edu.bot.hookahBot.customers.model.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.UUID;

@Transactional
@Repository
public interface CustomerRepo extends CrudRepository<Customer, Long> {}